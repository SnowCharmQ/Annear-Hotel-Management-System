package sustech.hotel.discount.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.discount.dao.OwnCouponDao;
import sustech.hotel.discount.entity.OwnCouponEntity;
import sustech.hotel.discount.service.OwnCouponService;


@Service("ownCouponService")
public class OwnCouponServiceImpl extends ServiceImpl<OwnCouponDao, OwnCouponEntity> implements OwnCouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OwnCouponEntity> page = this.page(
                new Query<OwnCouponEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}