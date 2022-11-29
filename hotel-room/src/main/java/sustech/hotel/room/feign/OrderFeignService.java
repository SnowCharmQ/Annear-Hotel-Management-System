package sustech.hotel.room.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sustech.hotel.common.utils.JsonResult;

@FeignClient("hotel-order")
public interface OrderFeignService {
    @GetMapping("/order/booking/getConflictList")
    JsonResult<String> getConflictList(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("hotelId") Long hotelId);

    @GetMapping("/order/order/getRoomTypeAverageScore")
    JsonResult<String> getAverageScore(@RequestParam("toList") String toList);

    @GetMapping("/order/ordercomments/getAllComments")
    JsonResult<String> getAllComments();
}
