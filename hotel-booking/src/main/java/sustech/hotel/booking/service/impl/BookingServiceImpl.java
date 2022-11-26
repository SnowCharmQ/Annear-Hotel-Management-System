package sustech.hotel.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.booking.dao.BookingDao;
import sustech.hotel.booking.feign.RoomFeignService;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;


import sustech.hotel.booking.entity.BookingEntity;
import sustech.hotel.booking.service.BookingService;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.model.to.hotel.ConflictTypeTo;


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
        ConflictTypeTo to = new ConflictTypeTo();
        List<Long> conflictList = bookingDao.selectConflictRoomByTimeIntervalAndHotel(startDate, endDate, hotelId);
        to.setHotelId(hotelId);
        to.setConflictList(conflictList);
        return roomFeignService.getAvailableRoomType(to);
    }

}