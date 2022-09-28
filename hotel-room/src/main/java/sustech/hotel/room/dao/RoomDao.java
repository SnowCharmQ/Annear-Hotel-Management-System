package sustech.hotel.room.dao;

import sustech.hotel.room.entity.RoomEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomDao extends BaseMapper<RoomEntity> {
	
}