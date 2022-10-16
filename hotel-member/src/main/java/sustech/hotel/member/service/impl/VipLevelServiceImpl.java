package sustech.hotel.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.member.dao.VipLevelDao;
import sustech.hotel.member.entity.VipLevelEntity;
import sustech.hotel.member.feign.OrderFeignService;
import sustech.hotel.member.service.VipLevelService;


@Service("vipLevelService")
public class VipLevelServiceImpl extends ServiceImpl<VipLevelDao, VipLevelEntity> implements VipLevelService {

    @Autowired
    OrderFeignService orderFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VipLevelEntity> page = this.page(
                new Query<VipLevelEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}