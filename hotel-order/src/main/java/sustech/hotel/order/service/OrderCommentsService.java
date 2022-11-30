package sustech.hotel.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.order.entity.OrderCommentsEntity;

import java.util.Map;

public interface OrderCommentsService extends IService<OrderCommentsEntity> {
    PageUtils queryPage(Map<String, Object> params);

    PageUtils getComments(Long typeId, Map<String, Object> params);
}