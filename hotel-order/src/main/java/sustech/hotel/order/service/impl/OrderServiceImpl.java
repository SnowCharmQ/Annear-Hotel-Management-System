package sustech.hotel.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.*;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.UserNotFoundException;
import sustech.hotel.exception.order.RoomNotAvailableException;
import sustech.hotel.exception.order.RoomNotFoundException;
import sustech.hotel.exception.order.UserNotLoginException;
import sustech.hotel.model.to.hotel.RoomTo;
import sustech.hotel.model.to.hotel.RoomTypeTo;
import sustech.hotel.model.to.member.UserTo;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.order.OrderConfirmRespVo;
import sustech.hotel.constant.OrderConstant;
import sustech.hotel.model.vo.order.OrderConfirmVo;
import sustech.hotel.order.dao.OrderDao;
import sustech.hotel.order.entity.OrderEntity;
import sustech.hotel.order.feign.MemberFeignService;
import sustech.hotel.order.feign.RoomFeignService;
import sustech.hotel.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    RoomFeignService roomFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

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
        Long userID = checkUserID(request.getUserToken());
        JsonResult<RoomTo> room = roomFeignService.getRoomByID(request.getRoomId());
        JsonResult<RoomTypeTo> roomType = roomFeignService.getRoomTypeByID(room.getData().getTypeId());
        OrderConfirmRespVo resp = new OrderConfirmRespVo();
        BeanUtils.copyProperties(roomType.getData(), resp);
        BeanUtils.copyProperties(room.getData(), resp);
        resp.setUnitPrice(roomType.getData().getPrice());
        Date start = DateConverter.convertStringToDate(request.getStartDate());
        Date end = DateConverter.convertStringToDate(request.getEndDate());
        long day = (end.getTime() - start.getTime()) / 86400000;
        resp.setTotalPrice(new BigDecimal(day).multiply(roomType.getData().getPrice()));
        resp.setStartDate(start);
        resp.setEndDate(end);
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(OrderConstant.USER_ORDER_TOKEN_PREFIX + userID, token, 15, TimeUnit.MINUTES);
        resp.setToken(token);
        // TODO: 2022/11/24 set after discount price
        resp.setFinalPrice(resp.getTotalPrice());
        return resp;
    }

    @Override
    public void placeOrder(OrderEntity request) {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        JsonResult<RoomTo> room = roomFeignService.getRoomByID(request.getRoomId());
        if (room.getData() == null)
            throw new RoomNotFoundException(ExceptionCodeEnum.ROOM_NOT_FOUND_EXCEPTION);
        wrapper.and(i -> i.eq("room_id", request.getRoomId()).gt("end_date", request.getStartDate()).lt("start_date", request.getEndDate()));
        List<OrderEntity> list = this.list(wrapper);
        if (!(list == null || list.isEmpty()))
            throw new RoomNotAvailableException(ExceptionCodeEnum.ROOM_NOT_AVAILABLE_EXCEPTION);
        request.setOrderStatus(0);
        request.setOrderId(IdWorker.getTimeId());

        JsonResult<RoomTypeTo> roomType = roomFeignService.getRoomTypeByID(room.getData().getTypeId());
        request.setOriginMoney(roomType.getData().getPrice());
        // TODO: 2022/11/16 Get the After Discount Money
        request.setAfterDiscount(request.getOriginMoney());
        this.baseMapper.insert(request);
    }

    @Override
    public Long checkUserID(String token) {
        Long userid = JwtHelper.getUserId(token);
        if (userid == null)
            throw new UserNotLoginException(ExceptionCodeEnum.USER_NOT_LOGIN_EXCEPTION);
        JsonResult<UserTo> user = memberFeignService.getUser(userid);
        if (user == null)
            throw new UserNotFoundException(ExceptionCodeEnum.USER_NOT_FOUND_EXCEPTION);
        return user.getData().getUserId();
    }

}