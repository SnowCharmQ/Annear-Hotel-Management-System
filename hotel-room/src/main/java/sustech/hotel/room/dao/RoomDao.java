package sustech.hotel.room.dao;

import org.apache.ibatis.annotations.Param;
import sustech.hotel.model.vo.hotel.BookingRoomInfoVo;
import sustech.hotel.room.entity.RoomEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomDao extends BaseMapper<RoomEntity> {

    List<Long> selectAvailableRoomId(@Param("conflict_list") List<Long> conflictList, @Param("hotel_id") Long hotelId, @Param("type_id") Long typeId);

    List<BookingRoomInfoVo> selectRoomByHotelIdAndLayoutId(@Param("hotel_id")Long hotelId, @Param("layout_id") Long layoutId);
}