package sustech.hotel.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.hotel.HotelTo;
import sustech.hotel.model.to.hotel.RoomInfoTo;
import sustech.hotel.model.to.hotel.RoomTo;
import sustech.hotel.model.to.hotel.RoomTypeTo;

@FeignClient("hotel-room")
public interface RoomFeignService {
    @RequestMapping("room/roomtype/info/{typeId}")
    JsonResult<RoomTypeTo> getRoomTypeByID(@PathVariable("typeId") Long typeId);

    @RequestMapping("room/room/info/{roomId}")
    JsonResult<RoomTo> getRoomByID(@PathVariable("roomId") Long roomId);

    @RequestMapping("room/hotel/info/{hotelId}")
    JsonResult<HotelTo> getHotelByID(@PathVariable("hotelId") Long hotelId);

    @RequestMapping("room/room/allInfo/{roomId}")
    JsonResult<RoomInfoTo> allInfo(@PathVariable("roomId") Long roomId);
}
