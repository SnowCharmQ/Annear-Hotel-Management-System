package sustech.hotel.room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.room.entity.HotelEntity;

import java.util.Map;

public interface HotelService extends IService<HotelEntity> {
    PageUtils queryPage(Map<String, Object> params);
}