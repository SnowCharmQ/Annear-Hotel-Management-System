package sustech.hotel.order.dao;

import sustech.hotel.order.entity.OrderCommentsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderCommentsDao extends BaseMapper<OrderCommentsEntity> {
	
}