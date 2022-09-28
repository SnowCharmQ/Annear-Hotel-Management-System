package sustech.hotel.booking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.booking.entity.BookingEntity;

import java.util.Map;

public interface BookingService extends IService<BookingEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

