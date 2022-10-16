package sustech.hotel.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.order.OrderTo;

@FeignClient("hotel-order")
public interface OrderFeignService {
    @RequestMapping("/order/order/test")
     JsonResult<OrderTo> save(@RequestBody OrderTo order);
}
