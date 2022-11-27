package sustech.hotel.room.dao;

import org.apache.ibatis.annotations.Param;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.room.entity.RoomEntity;
import sustech.hotel.room.entity.RoomTypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface RoomTypeDao extends BaseMapper<RoomTypeEntity> {
    BigDecimal selectAvgPriceByHotelId(@Param("hotel_id") Long hotelId);


    List<AvailableRoomTypeTo> selectAvailableRoomTypeByConflictListAndHotel(@Param("conflict_list") List<Long> conflictList, @Param("hotel_id") Long hotelId);
}