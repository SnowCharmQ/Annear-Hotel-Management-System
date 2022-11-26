package sustech.hotel.room.dao;

import org.apache.ibatis.annotations.Param;
import sustech.hotel.room.entity.RoomTypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface RoomTypeDao extends BaseMapper<RoomTypeEntity> {
	BigDecimal selectAvgPriceByHotelId(@Param("hotel_id") Long hotelId);
}