package sustech.hotel.discount.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.discount.dao.VipPriceDao;
import sustech.hotel.discount.entity.VipPriceEntity;
import sustech.hotel.discount.service.VipPriceService;


@Service("vipPriceService")
public class VipPriceServiceImpl extends ServiceImpl<VipPriceDao, VipPriceEntity> implements VipPriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VipPriceEntity> page = this.page(
                new Query<VipPriceEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}