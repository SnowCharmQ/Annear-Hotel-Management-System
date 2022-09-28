package sustech.hotel.discount.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.discount.dao.SeckillDao;
import sustech.hotel.discount.entity.SeckillEntity;
import sustech.hotel.discount.service.SeckillService;


@Service("seckillService")
public class SeckillServiceImpl extends ServiceImpl<SeckillDao, SeckillEntity> implements SeckillService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillEntity> page = this.page(
                new Query<SeckillEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}