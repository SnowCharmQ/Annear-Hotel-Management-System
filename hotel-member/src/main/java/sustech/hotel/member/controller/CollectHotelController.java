package sustech.hotel.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.common.utils.JwtHelper;
import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.order.UserNotLoginException;
import sustech.hotel.member.entity.CollectHotelEntity;
import sustech.hotel.member.entity.UserInfoEntity;
import sustech.hotel.member.feign.RoomFeignService;
import sustech.hotel.member.service.CollectHotelService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.member.service.UserInfoService;
import sustech.hotel.model.vo.hotel.HotelVo;


@RestController
@RequestMapping("member/collecthotel")
public class CollectHotelController {

    @Autowired
    private CollectHotelService collectHotelService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoomFeignService roomFeignService;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params) {
        PageUtils page = collectHotelService.queryPage(params);
        return new JsonResult<>(page);
    }


    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody CollectHotelEntity collectHotel) {
        collectHotelService.save(collectHotel);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody CollectHotelEntity collectHotel) {
        collectHotelService.updateById(collectHotel);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Long[] userIds) {
        collectHotelService.removeByIds(Arrays.asList(userIds));
        return new JsonResult<>();
    }

    @ResponseBody
    @RequestMapping("/collectHotel")
    public JsonResult<Void> collectHotelByUser(@RequestParam("token") String token, @RequestParam("hotelId") Long hotelId) {
        Long userId;
        try {
            userId = userInfoService.getUserId(token);
        } catch (UserNotLoginException e) {
            return new JsonResult<>(e);
        }
        List<CollectHotelEntity> list = collectHotelService.list(new QueryWrapper<CollectHotelEntity>()
                .and(o -> o.eq("user_id", userId).eq("hotel_id", hotelId)));
        if (list == null || list.isEmpty()) {
            collectHotelService.collectHotelByUser(userId, hotelId);
        }
        return new JsonResult<>();
    }

    @ResponseBody
    @RequestMapping("/cancelCollectHotel")
    public JsonResult<Void> cancelCollectHotelByUser(@RequestParam("token") String token, @RequestParam("hotelId") Long hotelId) {
        Long userId;
        try {
            userId = userInfoService.getUserId(token);
        } catch (UserNotLoginException e) {
            return new JsonResult<>(e);
        }
        collectHotelService.cancelCollectHotel(userId, hotelId);
        return new JsonResult<>();
    }

    @RequestMapping("/showCollectedHotel")
    public JsonResult<List<Long>> showCollectedHotel(@RequestParam("userId") Long userId) {
        return new JsonResult<>(collectHotelService.showCollectedHotel(userId));
    }

    @RequestMapping("/collectedList")
    public JsonResult<List<HotelVo>> getCollectedList(@RequestParam("userId") Long userId) {
        List<Long> hotelList = collectHotelService.showCollectedHotel(userId);
        return new JsonResult<>(roomFeignService.getCollectedList(hotelList).getData());
    }
}