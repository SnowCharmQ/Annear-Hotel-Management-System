package sustech.hotel.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.order.OrderTo;

import java.util.List;

@FeignClient("hotel-order")
public interface OrderFeignService {
    @RequestMapping("/order/order/queryByUser")
    JsonResult<List<OrderTo>> queryOrderByUser(@RequestBody Long userId);
}
