package sustech.hotel.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;

import java.util.List;

@FeignClient("hotel-room")
public interface RoomFeignService {
    @GetMapping("/room/roomtype/availableRoomType")
    JsonResult<List<AvailableRoomTypeTo>> getAvailableRoomType(@RequestParam("hotelId") Long hotelId, @RequestParam("json") String json);
}
