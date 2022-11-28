package sustech.hotel.order.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.common.utils.DateConverter;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.order.dao.BookingDao;
import sustech.hotel.order.entity.BookingEntity;
import sustech.hotel.order.service.BookingService;


@RestController
@RequestMapping("order/booking")
public class BookingController {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private BookingService bookingService;

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params) {
        PageUtils page = bookingService.queryPage(params);
        return new JsonResult<>(page);
    }


    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{userId}")
    public JsonResult<BookingEntity> info(@PathVariable("userId") Long userId) {
        BookingEntity booking = bookingService.getById(userId);
        return new JsonResult<>(booking);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody BookingEntity booking) {
        bookingService.save(booking);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody BookingEntity booking) {
        bookingService.updateById(booking);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Long[] userIds) {
        bookingService.removeByIds(Arrays.asList(userIds));
        return new JsonResult<>();
    }

    @GetMapping("/availableRoomType")
    public JsonResult<List<AvailableRoomTypeTo>> getAvailableRoomType(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, @RequestParam("hotelId") Long hotelId) {
        return new JsonResult<>(bookingService.findByTimeIntervalAndHotel(startDate, endDate, hotelId));
    }

    @ResponseBody
    @GetMapping("/getConflictList")
    public JsonResult<String> getConflictList(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("hotelId") Long hotelId) {
        List<Long> conflictList = bookingDao.selectConflictRoomByTimeIntervalAndHotel(DateConverter.convertStringToDate(startDate), DateConverter.convertStringToDate(endDate), hotelId);
        String json = JSON.toJSONString(conflictList);
        return new JsonResult<>(json);
    }
}