package sustech.hotel.room.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.room.dao.RoomTypeDao;
import sustech.hotel.room.entity.RoomTypeEntity;
import sustech.hotel.room.service.RoomTypeService;


@Service("roomTypeService")
public class RoomTypeServiceImpl extends ServiceImpl<RoomTypeDao, RoomTypeEntity> implements RoomTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoomTypeEntity> page = this.page(
                new Query<RoomTypeEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}