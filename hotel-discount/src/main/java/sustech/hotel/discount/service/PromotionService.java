package sustech.hotel.discount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.discount.entity.PromotionEntity;

import java.util.Map;

public interface PromotionService extends IService<PromotionEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

