package sustech.hotel.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.hotel.*;
import sustech.hotel.model.vo.hotel.BookingRoomInfoVo;
import sustech.hotel.model.to.order.OrderInfoTo;

import java.util.List;

@FeignClient("hotel-room")
public interface RoomFeignService {
    @RequestMapping("/room/roomtype/info/{typeId}")
    JsonResult<RoomTypeTo> getRoomTypeByID(@PathVariable("typeId") Long typeId);

    @RequestMapping("room/room/info/{roomId}")
    JsonResult<RoomTo> getRoomByID(@PathVariable("roomId") Long roomId);

    @RequestMapping("/room/room/orderinfo/{roomId}")
    JsonResult<OrderInfoTo> getOrderInfo(@PathVariable("roomId") Long roomId);

    @RequestMapping("/room/room/allInfo/{roomId}")
    JsonResult<RoomInfoTo> allInfo(@PathVariable("roomId") Long roomId);

    @GetMapping("/room/roomtype/availableRoomType")
    JsonResult<List<AvailableRoomTypeTo>> getAvailableRoomType(@RequestParam("hotelId") Long hotelId, @RequestParam("json") String json);

    @GetMapping("/room/room/floorRoomList")
     JsonResult<List<BookingRoomInfoVo>> getFloorRoomList(@RequestParam("hotel_id") Long hotelId, @RequestParam("floor") Long floor);

    @GetMapping("/room/room/availableRoom")
    JsonResult<List<Long>> getAvailableRoom(@RequestParam("hotel_id") Long hotelId, @RequestParam("type_id") Long typeId, @RequestParam("json") String json);

    @RequestMapping("/room/roomtype/getCommentInfo")
    JsonResult<List<CommentInfoTo>> getCommentInfo(@RequestBody List<Long> typeIds);
}
