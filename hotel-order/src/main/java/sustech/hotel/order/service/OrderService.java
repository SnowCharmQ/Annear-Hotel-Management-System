package sustech.hotel.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.order.OrderConfirmRespVo;
import sustech.hotel.model.vo.order.OrderConfirmVo;
import sustech.hotel.model.vo.order.PlaceOrderVo;
import sustech.hotel.order.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<OrderEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<OrderTo> queryOrderByUser(Long userId);

    void placeOrder(OrderEntity request);

    Long checkUserID(String token);

    OrderConfirmRespVo confirmOrder(OrderConfirmVo request);
}