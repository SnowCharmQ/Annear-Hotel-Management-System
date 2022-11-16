package sustech.hotel.order.dao;

import org.springframework.core.annotation.Order;
import sustech.hotel.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {

}