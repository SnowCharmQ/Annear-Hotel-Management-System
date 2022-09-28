package sustech.hotel.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.order.dao.OrderCommentsDao;
import sustech.hotel.order.entity.OrderCommentsEntity;
import sustech.hotel.order.service.OrderCommentsService;


@Service("orderCommentsService")
public class OrderCommentsServiceImpl extends ServiceImpl<OrderCommentsDao, OrderCommentsEntity> implements OrderCommentsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderCommentsEntity> page = this.page(
                new Query<OrderCommentsEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}