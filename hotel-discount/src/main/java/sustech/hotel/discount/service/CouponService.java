package sustech.hotel.discount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.discount.entity.CouponEntity;

import java.util.Map;

public interface CouponService extends IService<CouponEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

