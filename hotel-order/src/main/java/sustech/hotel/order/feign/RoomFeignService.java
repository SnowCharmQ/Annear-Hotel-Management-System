package sustech.hotel.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.hotel.RoomTo;
import sustech.hotel.model.to.hotel.RoomTypeTo;

@FeignClient("hotel-room")
public interface RoomFeignService {
    @RequestMapping("room/roomtype/info/{typeId}")
    JsonResult<RoomTypeTo> getRoomTypeByID(@PathVariable("typeId") Long typeId);

    @RequestMapping("room/room/info/{roomId}")
    JsonResult<RoomTo> getRoomByID(@PathVariable("roomId") Long roomId);
}
