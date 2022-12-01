package sustech.hotel.room.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.common.utils.DateConverter;
import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.others.InvalidDateException;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.model.to.hotel.CommentInfoTo;
import sustech.hotel.model.vo.hotel.RoomTypeInfoVo;
import sustech.hotel.model.vo.hotel.RoomTypeSearchVo;
import sustech.hotel.model.vo.hotel.RoomTypeVo;
import sustech.hotel.room.entity.RoomTypeEntity;
import sustech.hotel.room.service.RoomTypeService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;


@RestController
@RequestMapping("room/roomtype")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping("/getCommentInfo")
    public JsonResult<List<CommentInfoTo>> getCommentInfo(@RequestBody List<Long> typeIds) {
        HashSet<Long> set = new HashSet<>(typeIds);
        typeIds.clear();
        typeIds.addAll(set);
        List<CommentInfoTo> list = typeIds.stream().map(l -> roomTypeService.getCommentInfo(l)).toList();
        return new JsonResult<>(list);
    }

    @GetMapping("/search")
    public JsonResult<RoomTypeSearchVo> search(@RequestParam("hotelId") Long hotelId, @RequestParam("guests") Integer guests,
                                               @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
                                               @RequestParam("sortBy") String sortBy, @RequestParam("reversed") Boolean reversed,
                                               @RequestParam("lowest") BigDecimal lowest, @RequestParam("highest") BigDecimal highest,
                                               @RequestParam("breakfast") Boolean breakfast, @RequestParam("windows") Boolean windows,
                                               @RequestParam("television") Boolean television, @RequestParam("bathtub") Boolean bathtub,
                                               @RequestParam("thermos") Boolean thermos) {
        Date start = DateConverter.convertStringToDate(startDate);
        Date end = DateConverter.convertStringToDate(endDate);
        Date cur = DateConverter.currentDate();
        if (cur.getTime() > start.getTime() || start.getTime() >= end.getTime())
            return new JsonResult<>(new InvalidDateException(ExceptionCodeEnum.INVALID_DATE_EXCEPTION));
        RoomTypeSearchVo vo = roomTypeService.search(hotelId, guests, startDate, endDate, sortBy, reversed, lowest, highest, breakfast, windows, television, bathtub, thermos);
        return new JsonResult<>(vo);
    }

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

    @PostMapping("/addRoomType")
    public JsonResult<Long> addRoomType(@RequestBody RoomTypeVo entity) {
        return new JsonResult<>(this.roomTypeService.addRoomType(entity));
    }

    @GetMapping("/deleteType")
    public JsonResult<Void> deleteType(Long typeId) {
        System.out.println(typeId);
        try {
            this.roomTypeService.deleteType(typeId);
        } catch (BaseException e) {
            return new JsonResult<>(e);
        }
        return new JsonResult<>();
    }

    @PostMapping("/alterType")
    public JsonResult<Void> alterType(@RequestBody RoomTypeEntity roomType) {
        this.roomTypeService.alterType(roomType);
        return new JsonResult<>();
    }

    @GetMapping("/getRoomType")
    public JsonResult<List<RoomTypeInfoVo>> getRoomType(String hotel) {
        return new JsonResult<>(this.roomTypeService.getRoomType(hotel));
    }
}