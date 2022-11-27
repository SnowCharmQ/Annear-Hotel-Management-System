package sustech.hotel.member.dao;

import sustech.hotel.member.entity.CollectHotelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectHotelDao extends BaseMapper<CollectHotelEntity> {
    void collectHotelByUser(Long userId, Long hotelId);

    void cancelCollectHotel(Long userId, Long hotelId);

    List<Long> showCollectedHotels(Long userId);
}