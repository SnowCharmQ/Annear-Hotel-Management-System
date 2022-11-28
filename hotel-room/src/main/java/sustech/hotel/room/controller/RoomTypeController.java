package sustech.hotel.room.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.room.dao.RoomTypeDao;
import sustech.hotel.room.entity.RoomTypeEntity;
import sustech.hotel.room.service.RoomTypeService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;


@RestController
@RequestMapping("room/roomtype")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomTypeDao roomTypeDao;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params) {
        PageUtils page = roomTypeService.queryPage(params);
        return new JsonResult<>(page);
    }


    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{typeId}")
    public JsonResult<RoomTypeEntity> info(@PathVariable("typeId") Long typeId) {
        RoomTypeEntity roomType = roomTypeService.getById(typeId);
        return new JsonResult<>(roomType);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody RoomTypeEntity roomType) {
        roomTypeService.save(roomType);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody RoomTypeEntity roomType) {
        roomTypeService.updateById(roomType);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Long[] typeIds) {
        roomTypeService.removeByIds(Arrays.asList(typeIds));
        return new JsonResult<>();
    }

    @GetMapping("/availableRoomType")
    public JsonResult<List<AvailableRoomTypeTo>> getAvailableRoomType(@RequestParam("hotelId") Long hotelId, @RequestParam("json") String json) {
        List<Long> conflictList = JSON.parseArray(json, Long.class);
        List<AvailableRoomTypeTo> list = roomTypeService.getAvailableRoomType(hotelId, conflictList);
        return new JsonResult<>(list);
    }
}