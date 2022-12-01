package sustech.hotel.room.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.exception.BaseException;
import sustech.hotel.model.vo.hotel.*;
import sustech.hotel.room.dao.HotelDao;
import sustech.hotel.room.entity.HotelEntity;
import sustech.hotel.room.entity.RoomTypeEntity;
import sustech.hotel.room.service.HotelService;
import sustech.hotel.room.service.RoomTypeService;

import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping("room/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelDao hotelDao;


    @ResponseBody
    @GetMapping("/getHotels")
    public JsonResult<List<HotelVo>> getHotels() {
        List<HotelVo> vos = hotelService.list().stream().map(o -> {
            HotelVo vo = new HotelVo();
            BeanUtils.copyProperties(o, vo);
            return vo;
        }).toList();
        return new JsonResult<>(vos);
    }

    @ResponseBody
    @GetMapping("/search/hotel")
    public JsonResult<SearchRespVo> searchHotel(@RequestParam("token") String token, @RequestParam("sortBy") String sortBy,
                                                @RequestParam("reversed") Boolean reversed, @RequestParam("diningRoom") Boolean diningRoom,
                                                @RequestParam("parking") Boolean parking, @RequestParam("spa") Boolean spa,
                                                @RequestParam("bar") Boolean bar, @RequestParam("gym") Boolean gym,
                                                @RequestParam("chessRoom") Boolean chessRoom, @RequestParam("swimmingPool") Boolean swimmingPool,
                                                @RequestParam("lowest") BigDecimal lowest, @RequestParam("highest") BigDecimal highest,
                                                @RequestParam("location") String location) {
        SearchRespVo vo = hotelService.searchHotel(token, sortBy, reversed, diningRoom, parking, spa, bar, gym, chessRoom, swimmingPool, lowest, highest, location);
        return new JsonResult<>(vo);
    }

    @ResponseBody
    @GetMapping("/initSearch")
    public JsonResult<SearchRespVo> initSearch(@RequestParam("token") String token) {
        SearchRespVo respVo = hotelService.initSearch(token);
        return new JsonResult<>(respVo);
    }

    @ResponseBody
    @GetMapping("/initReserve")
    public JsonResult<ReserveRespVo> initReserve(ReserveReqVo vo) {
        try {
            ReserveRespVo resp = hotelService.initReserve(vo);
            return new JsonResult<>(resp);
        } catch (BaseException e) {
            return new JsonResult<>(e);
        }
    }

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params) {
        PageUtils page = hotelService.queryPage(params);
        return new JsonResult<>(page);
    }


    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{hotelId}")
    public JsonResult<HotelEntity> info(@PathVariable("hotelId") Long hotelId) {
        HotelEntity hotel = hotelService.getById(hotelId);
        return new JsonResult<>(hotel);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody HotelEntity hotel) {
        hotelService.save(hotel);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody HotelEntity hotel) {
        hotelService.updateById(hotel);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Long[] hotelIds) {
        hotelService.removeByIds(Arrays.asList(hotelIds));
        return new JsonResult<>();
    }

    @ResponseBody
    @GetMapping("/getMapInfo")
    public JsonResult<List<HotelMapVo>> getMapInfo() {
        return new JsonResult<>(hotelService.getMapInfo());
    }

    @ResponseBody
    @GetMapping("/initAdminRoom")
    public JsonResult<AdminRoomVo> getHotelByName(String name) {
        AdminRoomVo vo = new AdminRoomVo();
        HotelEntity hotel = hotelService.getOne(new QueryWrapper<HotelEntity>().eq("hotel_name", name));
        Long hotelId = hotel.getHotelId();
        List<RoomTypeEntity> roomTypes = roomTypeService.list(new QueryWrapper<RoomTypeEntity>().eq("hotel_id", hotelId));
        List<String> strings = roomTypes.stream().map(RoomTypeEntity::getTypeName).toList();
        vo.setHotelId(hotelId);
        vo.setHotelName(name);
        vo.setFloors(hotel.getFloors());
        vo.setTypeNames(strings);
        return new JsonResult<>(vo);
    }

    @PostMapping("/collectedList")
    public JsonResult<List<HotelVo>> getCollectedList(@RequestBody List<Long> hotelId) {
        List<HotelVo> list = new ArrayList<>();
        for (Long id : hotelId)
            list.add(hotelDao.selectByHotelId(id));
        return new JsonResult<>(list);
    }
}