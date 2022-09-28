package sustech.hotel.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.order.entity.OrderOperationEntity;

import java.util.Map;

public interface OrderOperationService extends IService<OrderOperationEntity> {
    PageUtils queryPage(Map<String, Object> params);
}