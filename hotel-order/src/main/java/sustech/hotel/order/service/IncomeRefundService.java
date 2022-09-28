package sustech.hotel.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.order.entity.IncomeRefundEntity;

import java.util.Map;

public interface IncomeRefundService extends IService<IncomeRefundEntity> {
    PageUtils queryPage(Map<String, Object> params);
}