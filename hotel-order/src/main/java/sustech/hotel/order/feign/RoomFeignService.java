package sustech.hotel.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.hotel.*;

import java.util.List;

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

    @GetMapping("/room/roomtype/availableRoomType")
    JsonResult<List<AvailableRoomTypeTo>> getAvailableRoomType(@RequestParam("hotelId") Long hotelId, @RequestParam("json") String json);

    @GetMapping("/room/room/availableRoom")
    JsonResult<List<Long>> getAvailableRoom(@RequestParam("hotel_id") Long hotelId, @RequestParam("type_id") Long typeId, @RequestParam("json") String json);

    @GetMapping("/room/roomtype/getCommentInfo")
    JsonResult<List<CommentInfoTo>> getCommentInfo(List<Long> typeIds);
}
