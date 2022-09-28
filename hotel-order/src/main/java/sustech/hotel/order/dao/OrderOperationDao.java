package sustech.hotel.order.dao;

import sustech.hotel.order.entity.OrderOperationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderOperationDao extends BaseMapper<OrderOperationEntity> {
	
}