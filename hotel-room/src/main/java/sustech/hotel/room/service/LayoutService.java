package sustech.hotel.room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.room.entity.LayoutEntity;

import java.util.Map;

public interface LayoutService extends IService<LayoutEntity> {
    PageUtils queryPage(Map<String, Object> params);
}