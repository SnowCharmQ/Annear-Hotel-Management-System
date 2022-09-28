package sustech.hotel.discount.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.discount.dao.PromotionDao;
import sustech.hotel.discount.entity.PromotionEntity;
import sustech.hotel.discount.service.PromotionService;


@Service("promotionService")
public class PromotionServiceImpl extends ServiceImpl<PromotionDao, PromotionEntity> implements PromotionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PromotionEntity> page = this.page(
                new Query<PromotionEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}