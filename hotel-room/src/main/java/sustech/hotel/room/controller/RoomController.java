package sustech.hotel.room.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sustech.hotel.room.entity.RoomEntity;
import sustech.hotel.room.service.RoomService;
import sustech.hotel.common.utils.Constant;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;

@Api("房间控制类")
@RestController
@RequestMapping("room/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = roomService.queryPage(params);
        return new JsonResult<>(Constant.OK, page);
    }


    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{roomId}")
    public JsonResult<RoomEntity> info(@PathVariable("roomId") Integer roomId){
		RoomEntity room = roomService.getById(roomId);
        return new JsonResult<>(Constant.OK, room);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody RoomEntity room){
		roomService.save(room);
        return new JsonResult<>(Constant.OK);
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody RoomEntity room){
		roomService.updateById(room);
        return new JsonResult<>(Constant.OK);
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Integer[] roomIds){
		roomService.removeByIds(Arrays.asList(roomIds));
        return new JsonResult<>(Constant.OK);
    }
}