package sustech.hotel.discount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.discount.entity.SeckillEntity;

import java.util.Map;

public interface SeckillService extends IService<SeckillEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

