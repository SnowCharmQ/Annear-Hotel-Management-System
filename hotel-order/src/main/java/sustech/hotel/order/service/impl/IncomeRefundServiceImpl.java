package sustech.hotel.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.order.dao.IncomeRefundDao;
import sustech.hotel.order.entity.IncomeRefundEntity;
import sustech.hotel.order.service.IncomeRefundService;


@Service("incomeRefundService")
public class IncomeRefundServiceImpl extends ServiceImpl<IncomeRefundDao, IncomeRefundEntity> implements IncomeRefundService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IncomeRefundEntity> page = this.page(
                new Query<IncomeRefundEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}