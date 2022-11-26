package sustech.hotel.room.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.order.HotelNotFoundException;
import sustech.hotel.model.vo.hotel.*;
import sustech.hotel.room.dao.HotelDao;
import sustech.hotel.room.dao.RoomTypeDao;
import sustech.hotel.room.entity.HotelEntity;
import sustech.hotel.room.entity.HotelPictureEntity;
import sustech.hotel.room.service.HotelPictureService;
import sustech.hotel.room.service.HotelService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;


@RestController
@RequestMapping("room/hotel")
public class HotelController {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomTypeDao roomTypeDao;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelPictureService hotelPictureService;

    @Autowired
    private ThreadPoolExecutor executor;

    @ResponseBody
    @GetMapping("/initSearch")
    public JsonResult<SearchRespVo> initSearch() {
        SearchRespVo respVo = new SearchRespVo();
        List<LocationVo> locations = hotelDao.selectAllLocations();
        List<HotelEntity> hotelEntities = hotelService.list();
        List<HotelVo> hotelVos = hotelEntities.stream().map(o -> {
            HotelVo hotelVo = new HotelVo();
            BeanUtils.copyProperties(o, hotelVo);
            CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
                BigDecimal avgPrice = roomTypeDao.selectAvgPriceByHotelId(o.getHotelId());
                hotelVo.setAveragePrice(avgPrice);
            }, executor);
            CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
                String picturePath = hotelPictureService.getOne(new QueryWrapper<HotelPictureEntity>()
                        .and(i -> i.eq("hotel_id", o.getHotelId()).eq("cover", 1))).getPicturePath();
                hotelVo.setHotelPicture(picturePath);
            }, executor);
            try {
                CompletableFuture.allOf(task1, task2).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            return hotelVo;
        }).toList();
        respVo.setHotels(hotelVos);
        respVo.setLocations(locations);
        return new JsonResult<>(respVo);
    }

    @ResponseBody
    @GetMapping("/initReserve")
    public JsonResult<ReserveRespVo> initReserve(ReserveReqVo vo) {
        ReserveRespVo resp = new ReserveRespVo();
        HotelEntity entity = hotelService.getById(vo.getHotelId());
        if (entity == null) {
            return new JsonResult<>(new HotelNotFoundException(ExceptionCodeEnum.HOTEL_NOT_FOUND_EXCEPTION));
        }
        List<HotelPictureEntity> pictures
                = hotelPictureService.list(new QueryWrapper<HotelPictureEntity>().eq("hotel_id", vo.getHotelId()));
        List<String> images = pictures.stream().map(HotelPictureEntity::getPicturePath).toList();
        resp.setImages(images);
        BeanUtils.copyProperties(entity, resp);
        return new JsonResult<>(resp);
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
}