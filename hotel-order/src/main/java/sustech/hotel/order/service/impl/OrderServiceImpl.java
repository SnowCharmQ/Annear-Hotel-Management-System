package sustech.hotel.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.JwtHelper;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.NotFoundException;
import sustech.hotel.exception.auth.RoomNotAvailableException;
import sustech.hotel.exception.auth.RoomNotFoundException;
import sustech.hotel.exception.auth.UserNotLoginException;
import sustech.hotel.model.to.hotel.RoomTo;
import sustech.hotel.model.to.hotel.RoomTypeTo;
import sustech.hotel.model.to.member.UserTo;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.order.PlaceOrderVo;
import sustech.hotel.order.dao.OrderDao;
import sustech.hotel.order.entity.OrderEntity;
import sustech.hotel.order.entity.OrderInfoEntity;
import sustech.hotel.order.feign.MemberFeignService;
import sustech.hotel.order.feign.RoomFeignService;
import sustech.hotel.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    RoomFeignService roomFeignService;

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
    public void placeOrder(OrderEntity request) {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("room_id", request.getRoomId()).gt("end_date", request.getStartDate()).lt("start_date", request.getEndDate()));
        List<OrderEntity> list = this.list(wrapper);
        if (!(list == null || list.isEmpty()))
            throw new RoomNotAvailableException(ExceptionCodeEnum.ROOM_NOT_AVAILABLE_EXCEPTION.getCode(), ExceptionCodeEnum.ROOM_NOT_AVAILABLE_EXCEPTION.getMessage());
        request.setOrderStatus(0);
        request.setOrderId(IdWorker.getTimeId());
        JsonResult<RoomTo> room = roomFeignService.getRoomByID(request.getRoomId());
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
            throw new UserNotLoginException(ExceptionCodeEnum.USER_NOT_LOGIN_EXCEPTION.getCode(), ExceptionCodeEnum.USER_NOT_LOGIN_EXCEPTION.getMessage());
        JsonResult<UserTo> user = memberFeignService.getUser(userid);
        if (user == null)
            throw new NotFoundException(ExceptionCodeEnum.NOT_FOUND_EXCEPTION.getCode(), ExceptionCodeEnum.NOT_FOUND_EXCEPTION.getMessage());
        return user.getData().getUserId();
    }

}