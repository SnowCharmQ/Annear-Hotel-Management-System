package sustech.hotel.booking.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.booking.dao.BookingDao;
import sustech.hotel.booking.entity.BookingEntity;
import sustech.hotel.booking.service.BookingService;


@Service("bookingService")
public class BookingServiceImpl extends ServiceImpl<BookingDao, BookingEntity> implements BookingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BookingEntity> page = this.page(
                new Query<BookingEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}