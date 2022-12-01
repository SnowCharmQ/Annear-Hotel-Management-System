package sustech.hotel.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import sustech.hotel.common.utils.*;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.UserNotFoundException;
import sustech.hotel.exception.order.*;
import sustech.hotel.exception.others.InvalidDateException;
import sustech.hotel.model.to.hotel.HotelTo;
import sustech.hotel.model.to.hotel.RoomInfoTo;
import sustech.hotel.model.to.hotel.RoomTo;
import sustech.hotel.model.to.hotel.RoomTypeTo;
import sustech.hotel.model.to.member.UserTo;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.member.UserVo;
import sustech.hotel.model.vo.order.OrderConfirmRespVo;
import sustech.hotel.constant.OrderConstant;
import sustech.hotel.model.vo.order.OrderConfirmVo;
import sustech.hotel.model.vo.order.PayAsyncVo;
import sustech.hotel.model.vo.order.PayVo;
import sustech.hotel.order.dao.BookingDao;
import sustech.hotel.order.dao.OrderDao;
import sustech.hotel.order.entity.BookingEntity;
import sustech.hotel.order.entity.OrderEntity;
import sustech.hotel.order.entity.OrderInfoEntity;
import sustech.hotel.order.entity.OrderOperationEntity;
import sustech.hotel.order.feign.MemberFeignService;
import sustech.hotel.order.feign.RoomFeignService;
import sustech.hotel.order.service.BookingService;
import sustech.hotel.order.service.OrderInfoService;
import sustech.hotel.order.service.OrderOperationService;
import sustech.hotel.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private RoomFeignService roomFeignService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderOperationService orderOperationService;

    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<OrderTo> queryOrderByUser(Long userId) {
        List<OrderEntity> orders = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().eq("user_id", userId));
        List<OrderTo> orderTos = new ArrayList<>();
        for (OrderEntity order : orders) {
            OrderTo to = new OrderTo();
            BeanUtils.copyProperties(order, to);
            orderTos.add(to);
        }
        return orderTos;
    }

    @Override
    public OrderConfirmRespVo confirmOrder(OrderConfirmVo request) {
        UserTo user = getUser(request.getUserToken());
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user, vo);
        JsonResult<RoomInfoTo> roomInfo = roomFeignService.allInfo(request.getRoomId());
        OrderConfirmRespVo resp = new OrderConfirmRespVo();
        BeanUtils.copyProperties(roomInfo.getData(), resp);
        BeanUtils.copyProperties(roomInfo.getData(), resp);
        BeanUtils.copyProperties(roomInfo.getData(), resp);
        resp.setUnitPrice(roomInfo.getData().getPrice());
        Date start = DateConverter.convertStringToDate(request.getStartDate());
        Date end = DateConverter.convertStringToDate(request.getEndDate());
        if (start.getTime() >= end.getTime())
            throw new InvalidDateException(ExceptionCodeEnum.INVALID_DATE_EXCEPTION.getCode(), "Start date should before end date.");
        long day = (end.getTime() - start.getTime()) / 86400000;
        resp.setTotalPrice(new BigDecimal(day).multiply(roomInfo.getData().getPrice()));
        resp.setStartDate(start);
        resp.setEndDate(end);
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(OrderConstant.USER_ORDER_TOKEN_PREFIX + user.getUserId(), token, 15, TimeUnit.MINUTES);
        resp.setToken(token);
        // TODO: 2022/11/24 set after discount price
        resp.setFinalPrice(resp.getTotalPrice());
        resp.setUser(vo);
        return resp;
    }

    @Override
    public PayVo getOrderPay(String orderId) {
        OrderEntity order = baseMapper.selectById(orderId);
        if (order == null)
            throw new OrderNotExistException(ExceptionCodeEnum.ORDER_NOT_EXIST_EXCEPTION);
        if (order.getOrderStatus() != 0)
            throw new OrderClosedException(ExceptionCodeEnum.ORDER_CLOSED_EXCEPTION);
        PayVo payVo = new PayVo();
        BigDecimal bigDecimal = order.getAfterDiscount().setScale(2, BigDecimal.ROUND_UP);
        payVo.setTotal_amount(bigDecimal.toString());
        payVo.setOut_trade_no(orderId);
        payVo.setSubject("Annear Hotel Payment");
        payVo.setBody("");
        return payVo;
    }

    @Override
    public String handlePayResult(PayAsyncVo vo) {
        OrderOperationEntity operation = new OrderOperationEntity();
        operation.setOperationTime(new Timestamp(vo.getNotify_time().getTime()));
        operation.setOrderId(vo.getOut_trade_no());
        if (vo.getTrade_status().equals("TRADE_SUCCESS") || vo.getTrade_status().equals("TRADE_FINISHED")) {
            String orderId = vo.getOut_trade_no();
            this.baseMapper.updateOrderStatus(orderId, 1);
            bookingDao.updateOrderStatus(orderId, 1);
            operation.setOperation(1);
        } else
            operation.setOperation(2);
        orderOperationService.save(operation);
        return "success";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String placeOrder(OrderEntity request, List<String> guestInfo, String orderToken) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long result = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class),
                List.of(OrderConstant.USER_ORDER_TOKEN_PREFIX + request.getUserId()),
                orderToken);
        if (result == null || result == 0L)
            //fail
            throw new CreateOrderException(ExceptionCodeEnum.CREATE_ORDER_EXCEPTION);
        //success
        Date currentDate = DateConverter.currentDate();
        if (request.getStartDate().getTime() < currentDate.getTime())
            throw new InvalidDateException(ExceptionCodeEnum.INVALID_DATE_EXCEPTION.getCode(), "Start date should at least be today.");
        if (request.getStartDate().getTime() >= request.getEndDate().getTime())
            throw new InvalidDateException(ExceptionCodeEnum.INVALID_DATE_EXCEPTION.getCode(), "Start Date should before end Date");
        JsonResult<RoomTo> room = roomFeignService.getRoomByID(request.getRoomId());
        if (room.getData() == null)
            throw new RoomNotFoundException(ExceptionCodeEnum.ROOM_NOT_FOUND_EXCEPTION);
        bookingService.checkAvailable(request.getRoomId(), request.getStartDate(), request.getEndDate());
        request.setOrderStatus(0);
        request.setOrderId(IdWorker.getTimeId());
        BeanUtils.copyProperties(room.getData(), request);
        JsonResult<RoomTypeTo> roomType = roomFeignService.getRoomTypeByID(room.getData().getTypeId());
        if (guestInfo.size() > roomType.getData().getUpperLimit())
            throw new GuestNumberExceedLimitException(ExceptionCodeEnum.GUEST_NUMBER_EXCEED_LIMIT_EXCEPTION);
        request.setOriginMoney(roomType.getData().getPrice());
        // TODO: 2022/11/27 Get the After Discount Money
        request.setAfterDiscount(request.getOriginMoney());
        this.baseMapper.insert(request);
        BookingEntity bookingEntity = new BookingEntity();
        BeanUtils.copyProperties(request, bookingEntity);
        bookingEntity.setHotelId(roomType.getData().getHotelId());
        bookingEntity.setTypeId(roomType.getData().getTypeId());
        bookingEntity.setOrderState(0);
        bookingService.save(bookingEntity);
        for (String s : guestInfo) {
            if (StringUtils.isEmpty(s)) continue;
            String[] info = s.split(",");
            if (info.length == 0) continue;
            if (!info[0].equals(" ") || !info[1].equals(" ") || !info[2].equals(" ")) {
                OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
                orderInfoEntity.setOrderId(request.getOrderId());
                orderInfoEntity.setTenantName(info[0]);
                orderInfoEntity.setTelephone(info[1]);
                orderInfoEntity.setIdentityCard(info[2]);
                orderInfoService.save(orderInfoEntity);
            }
        }
        OrderOperationEntity orderOperationEntity = new OrderOperationEntity();
        orderOperationEntity.setOperation(0);
        orderOperationEntity.setOperationTime(new Timestamp(System.currentTimeMillis()));
        orderOperationEntity.setOrderId(request.getOrderId());
        orderOperationService.save(orderOperationEntity);
        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", request);
        return request.getOrderId();
    }

    @Override
    public Long checkUserId(String token) {
        return this.getUser(token).getUserId();
    }


    @Override
    public UserTo getUser(String token) {
        Long userid;
        try {
            userid = JwtHelper.getUserId(token);
            if (userid == null)
                throw new UserNotLoginException(ExceptionCodeEnum.USER_NOT_LOGIN_EXCEPTION);
        } catch (Exception e) {
            throw new UserNotLoginException(ExceptionCodeEnum.USER_NOT_LOGIN_EXCEPTION);
        }
        JsonResult<UserTo> user = memberFeignService.getUser(userid);
        if (user == null || user.getData() == null)
            throw new UserNotFoundException(ExceptionCodeEnum.USER_NOT_FOUND_EXCEPTION);
        return user.getData();
    }

    @Override
    public void closeOrder(OrderEntity orderEntity) {
        //关闭订单之前先查询一下数据库，判断此订单状态是否已支付
        OrderEntity orderInfo = this.baseMapper.selectById(orderEntity.getOrderId());

        if (orderInfo.getOrderStatus().equals(0)) {
            //代付款状态进行关单
            this.baseMapper.updateOrderStatus(orderInfo.getOrderId(), 2);
            bookingDao.deleteByOrderId(orderEntity.getOrderId());
            // 发送消息给MQ
            OrderTo orderTo = new OrderTo();
            BeanUtils.copyProperties(orderInfo, orderTo);

            try {
                //TODO 确保每个消息发送成功，给每个消息做好日志记录，(给数据库保存每一个详细信息)保存每个消息的详细信息
                rabbitTemplate.convertAndSend("order-event-exchange", "order.release.other", orderTo);
            } catch (Exception e) {
                //TODO 定期扫描数据库，重新发送失败的消息
            }
            OrderOperationEntity orderOperationEntity = new OrderOperationEntity();
            orderOperationEntity.setOperation(2);
            orderOperationEntity.setOrderId(orderEntity.getOrderId());
            orderOperationEntity.setOperationTime(new Timestamp(System.currentTimeMillis()));
            orderOperationService.save(orderOperationEntity);
        }
    }




}