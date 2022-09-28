package sustech.hotel.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.order.entity.OrderInfoEntity;

import java.util.Map;

public interface OrderInfoService extends IService<OrderInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);
}