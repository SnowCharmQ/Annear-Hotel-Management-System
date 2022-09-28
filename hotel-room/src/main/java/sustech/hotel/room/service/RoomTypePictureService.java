package sustech.hotel.room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.room.entity.RoomTypePictureEntity;

import java.util.Map;

public interface RoomTypePictureService extends IService<RoomTypePictureEntity> {
    PageUtils queryPage(Map<String, Object> params);
}