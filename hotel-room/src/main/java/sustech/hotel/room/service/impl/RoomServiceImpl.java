package sustech.hotel.room.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.Constant;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.room.dao.RoomDao;
import sustech.hotel.room.entity.RoomEntity;
import sustech.hotel.room.service.LayoutService;
import sustech.hotel.room.service.RoomService;


@Service("roomService")
public class RoomServiceImpl extends ServiceImpl<RoomDao, RoomEntity> implements RoomService {

    @Autowired
    LayoutService layoutService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        int hotelId = Integer.parseInt(params.get("hotel").toString());
        List<RoomEntity> entities = this.baseMapper.selectList(new QueryWrapper<RoomEntity>().eq("hotel_id", hotelId));
        int curPage = 1;
        int limit = 10;
        if (params.get(Constant.PAGE) != null) {
            curPage = Integer.parseInt(params.get(Constant.PAGE).toString());
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Integer.parseInt(params.get(Constant.LIMIT).toString());
        }
        return new PageUtils(entities, entities.size(), limit, curPage);
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