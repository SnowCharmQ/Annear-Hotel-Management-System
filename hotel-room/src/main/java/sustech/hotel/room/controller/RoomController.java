package sustech.hotel.room.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.model.to.hotel.RoomInfoTo;
import sustech.hotel.model.to.order.OrderInfoTo;
import sustech.hotel.room.entity.HotelEntity;
import sustech.hotel.room.entity.RoomEntity;
import sustech.hotel.room.entity.RoomTypeEntity;
import sustech.hotel.room.service.HotelService;
import sustech.hotel.room.service.RoomService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.room.service.RoomTypeService;

@Api("房间控制类")
@RestController
@RequestMapping("room/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/allInfo/{roomId}")
    public JsonResult<RoomInfoTo> allInfo(@PathVariable("roomId") Long roomId) {
        RoomInfoTo roomInfoTo = new RoomInfoTo();
        RoomEntity room = roomService.getById(roomId);
        RoomTypeEntity roomType = roomTypeService.getById(room.getTypeId());
        HotelEntity hotel = hotelService.getById(room.getHotelId());
        BeanUtils.copyProperties(room, roomInfoTo);
        BeanUtils.copyProperties(roomType, roomInfoTo);
        BeanUtils.copyProperties(hotel, roomInfoTo);
        return new JsonResult<>(roomInfoTo);
    }

    @RequestMapping("/orderinfo/{roomId}")
    public JsonResult<OrderInfoTo> getOrderInfo(@PathVariable("roomId") Long roomId) {
        RoomEntity room = roomService.getById(roomId);
        Long hotelId = room.getHotelId();
        HotelEntity hotel = hotelService.getById(hotelId);
        Long roomNumber = room.getRoomNumber();
        String hotelName = hotel.getHotelName();
        OrderInfoTo to = new OrderInfoTo();
        to.setRoomNumber(roomNumber);
        to.setHotelName(hotelName);
        return new JsonResult<>(to);
    }

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params) {
        PageUtils page = roomService.queryPage(params);
        return new JsonResult<>(page);
    }


    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{roomId}")
    public JsonResult<RoomEntity> info(@PathVariable("roomId") Long roomId) {
        RoomEntity room = roomService.getById(roomId);
        return new JsonResult<>(room);
    }


    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody RoomEntity room) {
        roomService.save(room);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody RoomEntity room) {
        roomService.updateById(room);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Long[] roomIds) {
        roomService.removeByIds(Arrays.asList(roomIds));
        return new JsonResult<>();
    }

    @GetMapping("/availableRoom")
    public JsonResult<List<Long>> getAvailableRoom(@RequestParam("hotel_id") Long hotelId, @RequestParam("type_id") Long typeId, @RequestParam("json") String json) {
        List<Long> conflictList = JSON.parseArray(json, Long.class);
        List<Long> list = roomService.getAvailableRoom(hotelId, typeId, conflictList);
        return new JsonResult<>(list);
    }
}