package sustech.hotel.discount.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.discount.entity.OwnCouponEntity;

import java.util.Map;

public interface OwnCouponService extends IService<OwnCouponEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

