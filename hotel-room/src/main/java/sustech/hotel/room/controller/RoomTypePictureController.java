package sustech.hotel.room.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sustech.hotel.room.entity.RoomTypePictureEntity;
import sustech.hotel.room.service.RoomTypePictureService;
import sustech.hotel.common.utils.Constant;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;


@RestController
@RequestMapping("room/roomtypepicture")
public class RoomTypePictureController {

    @Autowired
    private RoomTypePictureService roomTypePictureService;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = roomTypePictureService.queryPage(params);
        return new JsonResult<>(page);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody RoomTypePictureEntity roomTypePicture){
		roomTypePictureService.save(roomTypePicture);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody RoomTypePictureEntity roomTypePicture){
		roomTypePictureService.updateById(roomTypePicture);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Long[] typeIds){
		roomTypePictureService.removeByIds(Arrays.asList(typeIds));
        return new JsonResult<>();
    }
}