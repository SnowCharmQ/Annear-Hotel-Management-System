package sustech.hotel.room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.room.entity.HotelPictureEntity;

import java.util.Map;

public interface HotelPictureService extends IService<HotelPictureEntity> {
    PageUtils queryPage(Map<String, Object> params);
}