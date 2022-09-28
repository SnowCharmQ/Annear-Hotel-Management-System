package sustech.hotel.room.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.room.dao.RoomDao;
import sustech.hotel.room.entity.RoomEntity;
import sustech.hotel.room.service.RoomService;


@Service("roomService")
public class RoomServiceImpl extends ServiceImpl<RoomDao, RoomEntity> implements RoomService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoomEntity> page = this.page(
                new Query<RoomEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}