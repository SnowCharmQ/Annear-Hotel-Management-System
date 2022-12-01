package sustech.hotel.room.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<Long> getAvailableRoom(Long hotelId, Long typeId, List<Long> conflictList) {
        if (conflictList == null || conflictList.isEmpty()) {
            conflictList = new ArrayList<>();
            conflictList.add(-1L);
        }
        return this.baseMapper.selectAvailableRoomId(conflictList, hotelId, typeId);
    }

    @Override
    public boolean typeExist(Long typeId){
        List<RoomEntity> rooms = this.baseMapper.selectList(new QueryWrapper<RoomEntity>().eq("type_id", typeId));
        return !rooms.isEmpty();
    }
}