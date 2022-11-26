package sustech.hotel.booking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.booking.entity.BookingEntity;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.model.to.hotel.RoomTypeTo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface BookingService extends IService<BookingEntity> {
    PageUtils queryPage(Map<String, Object> params);

    JsonResult<List<AvailableRoomTypeTo>> findByTimeIntervalAndHotel(Date startDate, Date endDate, Long hotelId);
}

