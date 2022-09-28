package sustech.hotel.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.order.dao.OrderOperationDao;
import sustech.hotel.order.entity.OrderOperationEntity;
import sustech.hotel.order.service.OrderOperationService;


@Service("orderOperationService")
public class OrderOperationServiceImpl extends ServiceImpl<OrderOperationDao, OrderOperationEntity> implements OrderOperationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderOperationEntity> page = this.page(
                new Query<OrderOperationEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}