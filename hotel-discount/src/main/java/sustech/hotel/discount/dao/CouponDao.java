package sustech.hotel.discount.dao;

import sustech.hotel.discount.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}