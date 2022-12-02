package sustech.hotel.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.model.to.member.UserTo;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.order.*;
import sustech.hotel.order.entity.OrderEntity;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface OrderService extends IService<OrderEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<OrderTo> queryOrderByUser(Long userId);

    String placeOrder(OrderEntity request, List<String> guestInfo, String orderToken);

    Long checkUserId(String token);

    OrderConfirmRespVo confirmOrder(OrderConfirmVo request);

    PayVo getOrderPay(String orderId);

    String handlePayResult(PayAsyncVo vo);

    UserTo getUser(String token);

    void closeOrder(OrderEntity orderEntity);

    PageUtils getUserOrders(Map<String, Object> params);

    void sendMail(String orderId, String to, Date startDate, Date endDate);

//    void modifyOrder(String orderId, String phone, String )
}