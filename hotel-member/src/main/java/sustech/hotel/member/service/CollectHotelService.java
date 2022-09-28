package sustech.hotel.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.member.entity.CollectHotelEntity;

import java.util.Map;

public interface CollectHotelService extends IService<CollectHotelEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

