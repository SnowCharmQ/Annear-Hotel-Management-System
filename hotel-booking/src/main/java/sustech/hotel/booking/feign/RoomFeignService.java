package sustech.hotel.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.model.to.hotel.ConflictTypeTo;

import java.util.List;

@FeignClient("hotel-room")
public interface RoomFeignService {
    @GetMapping("room/roomtype/availableRoomType")
    JsonResult<List<AvailableRoomTypeTo>> getAvailableRoomType(@RequestBody ConflictTypeTo to);
}
