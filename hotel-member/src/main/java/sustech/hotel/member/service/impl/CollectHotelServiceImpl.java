package sustech.hotel.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.member.dao.CollectHotelDao;
import sustech.hotel.member.entity.CollectHotelEntity;
import sustech.hotel.member.service.CollectHotelService;


@Service("collectHotelService")
public class CollectHotelServiceImpl extends ServiceImpl<CollectHotelDao, CollectHotelEntity> implements CollectHotelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectHotelEntity> page = this.page(
                new Query<CollectHotelEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}