package sustech.hotel.booking.dao;

import sustech.hotel.booking.entity.BookingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookingDao extends BaseMapper<BookingEntity> {
	
}