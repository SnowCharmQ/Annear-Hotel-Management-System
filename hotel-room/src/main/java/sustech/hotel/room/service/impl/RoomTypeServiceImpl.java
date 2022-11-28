package sustech.hotel.room.service.impl;

import com.alibaba.fastjson2.JSON;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
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

    @Override
    public List<AvailableRoomTypeTo> getAvailableRoomType(Long hotelId, List<Long> conflictList) {
        if (conflictList == null || conflictList.isEmpty()) {
            conflictList = new ArrayList<>();
            conflictList.add(-1L);
        }
        return this.baseMapper.selectAvailableRoomTypeByConflictListAndHotel(conflictList, hotelId);
    }

}