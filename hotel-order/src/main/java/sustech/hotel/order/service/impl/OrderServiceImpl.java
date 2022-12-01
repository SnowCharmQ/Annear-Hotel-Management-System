package sustech.hotel.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import sustech.hotel.common.utils.*;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.UserNotFoundException;
import sustech.hotel.exception.order.*;
import sustech.hotel.exception.others.InvalidDateException;
import sustech.hotel.model.to.hotel.RoomInfoTo;
import sustech.hotel.model.to.hotel.RoomTo;
import sustech.hotel.model.to.hotel.RoomTypeTo;
import sustech.hotel.model.to.member.UserTo;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.member.UserVo;
import sustech.hotel.model.vo.order.*;
import sustech.hotel.constant.OrderConstant;
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
    private OrderDao orderDao;

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
        int status = -1;
        int roomId = -1;
        Date date1 = null;
        Date date2 = null;
        if (params.get("status") != null) {
            status = Integer.parseInt(params.get("status").toString());
        }
        if (params.get("roomId") != null) {
            roomId = Integer.parseInt(params.get("roomId").toString());
        }
        if (params.get("date1") != null && !params.get("date1").equals("0")) {
            date1 = new Date(Long.parseLong(params.get("date1").toString()));
        }
        if (params.get("date2") != null && !params.get("date2").equals("0")) {
            date2 = new Date(Long.parseLong(params.get("date2").toString()));
        }
        int hotelId = Integer.parseInt(params.get("hotel").toString());
        List<OrderEntity> entities = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().eq("hotel_id", hotelId));

        if (status != -1) {
            int finalStatus = status;
            entities = entities.stream().filter(entitie -> entitie.getOrderStatus() == finalStatus).toList();
        }
        if (roomId!= -1){
            int finalType = roomId;
            entities = entities.stream().filter(entitie -> entitie.getRoomId() == finalType).toList();
        }
        if (date1!=null){
            Date finalDate = date1;
            entities = entities.stream().filter(entitie -> entitie.getStartDate().equals(finalDate)).toList();
        }
        if (date2!=null){
            Date finalDate1 = date2;
            entities = entities.stream().filter(entitie -> entitie.getEndDate().equals(finalDate1)).toList();
        }
        int curPage = 1;
        int limit = 10;
        if (params.get(Constant.PAGE) != null) {
            curPage = Integer.parseInt(params.get(Constant.PAGE).toString());
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Integer.parseInt(params.get(Constant.LIMIT).toString());
        }
        return new PageUtils(entities, entities.size(), limit, curPage);
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

    @Override
    public PageUtils getUserOrders(Map<String, Object> params) {
        String token = (String) params.get("token");
        Long userId = this.checkUserId(token);
        List<OrderEntity> entities = this.list(new QueryWrapper<OrderEntity>().eq("user_id", userId));
        List<OrderTo> tos = entities.stream().map(o -> {
            OrderTo to = new OrderTo();
            BeanUtils.copyProperties(o, to);
            return to;
        }).toList();
        String json = JSON.toJSONString(tos);
        String data = roomFeignService.getOrderRoomInfo(json).getData();
        List<OrderShowVo> list = JSON.parseArray(data, OrderShowVo.class);
        int curPage = 1;
        int limit = 10;
        if (params.get(Constant.PAGE) != null) {
            curPage = Integer.parseInt(params.get(Constant.PAGE).toString());
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Integer.parseInt(params.get(Constant.LIMIT).toString());
        }
        return new PageUtils(list, list.size(), limit, curPage);
    }


    @Scheduled(cron = "0 1 12 * * ?")
    public void automaticUpdateOrderStatus() {
        orderDao.automaticUpdateOrderStatus();
    }
}