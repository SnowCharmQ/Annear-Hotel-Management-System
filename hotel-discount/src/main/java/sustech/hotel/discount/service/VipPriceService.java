package sustech.hotel.discount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.discount.entity.VipPriceEntity;

import java.util.Map;

public interface VipPriceService extends IService<VipPriceEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

