package sustech.hotel.room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.room.entity.RoomTypeEntity;

import java.util.Map;

public interface RoomTypeService extends IService<RoomTypeEntity> {
    PageUtils queryPage(Map<String, Object> params);
}