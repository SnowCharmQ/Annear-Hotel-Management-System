package sustech.hotel.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.member.entity.CollectHotelEntity;

import java.util.List;
import java.util.Map;

public interface CollectHotelService extends IService<CollectHotelEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void collectHotelByUser(long userId, int hotelId);

    void cancelCollectHotel(long userId, int hotelId);

    List<Integer> showCollectedHotel(long userId);
}

