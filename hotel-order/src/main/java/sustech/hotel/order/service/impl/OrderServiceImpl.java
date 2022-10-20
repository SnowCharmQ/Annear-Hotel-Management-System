package sustech.hotel.order.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.order.dao.OrderDao;
import sustech.hotel.order.entity.OrderEntity;
import sustech.hotel.order.entity.OrderInfoEntity;
import sustech.hotel.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<OrderTo> queryOrderByUser(Long userId) {
        List<OrderEntity> orders = this.baseMapper.selectList(new QueryWrapper<OrderEntity>().eq("user_id", userId));
        List<OrderTo> orderTos = new ArrayList<>();
        for(OrderEntity order: orders){
            OrderTo to = new OrderTo();
            BeanUtils.copyProperties(order, to);
            orderTos.add(to);
        }
        return orderTos;
    }
}