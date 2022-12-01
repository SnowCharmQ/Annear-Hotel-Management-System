package sustech.hotel.room.service.impl;

import org.springframework.beans.BeanUtils;
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

import sustech.hotel.model.vo.hotel.RoomVo;
import sustech.hotel.room.dao.RoomDao;
import sustech.hotel.room.entity.RoomEntity;
import sustech.hotel.room.entity.RoomTypeEntity;
import sustech.hotel.room.service.LayoutService;
import sustech.hotel.room.service.RoomService;
import sustech.hotel.room.service.RoomTypeService;


@Service("roomService")
public class RoomServiceImpl extends ServiceImpl<RoomDao, RoomEntity> implements RoomService {

    @Autowired
    LayoutService layoutService;

    @Autowired
    RoomTypeService roomTypeService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        int hotelId = Integer.parseInt(params.get("hotel").toString());
        List<RoomEntity> entities = this.baseMapper.selectList(new QueryWrapper<RoomEntity>().eq("hotel_id", hotelId));
        List<RoomVo> vos = entities.stream().map(o -> {
            RoomVo vo = new RoomVo();
            BeanUtils.copyProperties(o, vo);
            RoomTypeEntity type = roomTypeService.getById(vo.getTypeId());
            vo.setTypeName(type.getTypeName());
            return vo;
        }).toList();
        int curPage = 1;
        int limit = 10;
        if (params.get(Constant.PAGE) != null) {
            curPage = Integer.parseInt(params.get(Constant.PAGE).toString());
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Integer.parseInt(params.get(Constant.LIMIT).toString());
        }
        return new PageUtils(vos, vos.size(), limit, curPage);
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