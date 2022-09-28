package sustech.hotel.room.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.room.dao.LayoutDao;
import sustech.hotel.room.entity.LayoutEntity;
import sustech.hotel.room.service.LayoutService;


@Service("layoutService")
public class LayoutServiceImpl extends ServiceImpl<LayoutDao, LayoutEntity> implements LayoutService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LayoutEntity> page = this.page(
                new Query<LayoutEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}