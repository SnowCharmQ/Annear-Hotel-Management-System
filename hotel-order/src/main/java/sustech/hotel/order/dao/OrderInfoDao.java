package sustech.hotel.order.dao;

import sustech.hotel.order.entity.OrderInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoDao extends BaseMapper<OrderInfoEntity> {
	
}