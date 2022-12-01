package sustech.hotel.room.dao;

import org.apache.ibatis.annotations.Param;
import sustech.hotel.model.vo.hotel.HotelVo;
import sustech.hotel.model.vo.hotel.LocationVo;
import sustech.hotel.room.entity.HotelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelDao extends BaseMapper<HotelEntity> {
	List<LocationVo> selectAllLocations();

	HotelVo selectByHotelId(@Param("hotel_id") Long hotelId);
}