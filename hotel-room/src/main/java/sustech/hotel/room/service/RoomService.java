package sustech.hotel.room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.room.entity.RoomEntity;

import java.util.Map;

public interface RoomService extends IService<RoomEntity> {
    PageUtils queryPage(Map<String, Object> params);
}