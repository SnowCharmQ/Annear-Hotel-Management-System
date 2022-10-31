package sustech.hotel.member.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sustech.hotel.member.entity.CollectHotelEntity;
import sustech.hotel.member.service.CollectHotelService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;


@RestController
@RequestMapping("member/collecthotel")
public class CollectHotelController {

    @Autowired
    private CollectHotelService collectHotelService;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = collectHotelService.queryPage(params);
        return new JsonResult<>(page);
    }


    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody CollectHotelEntity collectHotel){
		collectHotelService.save(collectHotel);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody CollectHotelEntity collectHotel){
		collectHotelService.updateById(collectHotel);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Long[] userIds){
		collectHotelService.removeByIds(Arrays.asList(userIds));
        return new JsonResult<>();
    }

    @RequestMapping("/collectHotel")
    public JsonResult<Void> collectHotelByUser(long userId, int hotelId){
        collectHotelService.collectHotelByUser(userId, hotelId);
        return new JsonResult<>();
    }

    @RequestMapping("/cancelCollectHotel")
    public JsonResult<Void> cancelCollectHotelByUser(long userId, int hotelId){
        collectHotelService.cancelCollectHotel(userId, hotelId);
        return new JsonResult<>();
    }

    @RequestMapping("/showCollectedHotel")
    public JsonResult<List<Integer>> showCollectedHotel(long userId){
        return new JsonResult<>(collectHotelService.showCollectedHotel(userId));
    }
}