package sustech.hotel.order.service.impl;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;


import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.order.RoomNotAvailableException;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.order.dao.BookingDao;
import sustech.hotel.order.entity.BookingEntity;
import sustech.hotel.order.feign.RoomFeignService;
import sustech.hotel.order.service.BookingService;


@Service("bookingService")
public class BookingServiceImpl extends ServiceImpl<BookingDao, BookingEntity> implements BookingService {

    @Autowired
    private RoomFeignService roomFeignService;
    @Autowired
    private BookingDao bookingDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookingEntity> page = this.page(
                new Query<BookingEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public JsonResult<List<AvailableRoomTypeTo>> findByTimeIntervalAndHotel(Date startDate, Date endDate, Long hotelId) {
        List<Long> conflictList = bookingDao.selectConflictRoomByTimeIntervalAndHotel(startDate, endDate, hotelId);
        String json = JSON.toJSONString(conflictList);
        return roomFeignService.getAvailableRoomType(hotelId, json);
    }

    @Override
    public void checkAvailable(Long roomId, Date startDate, Date endDate) {
        QueryWrapper<BookingEntity> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.eq("room_id", roomId).gt("end_date", startDate).lt("start_date", endDate));
        List<BookingEntity> list = this.list(wrapper);
        if (!(list == null || list.isEmpty()))
            throw new RoomNotAvailableException(ExceptionCodeEnum.ROOM_NOT_AVAILABLE_EXCEPTION);
    }
}