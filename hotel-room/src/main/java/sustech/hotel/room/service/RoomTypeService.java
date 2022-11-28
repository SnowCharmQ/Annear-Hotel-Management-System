package sustech.hotel.room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.model.vo.hotel.RoomTypeSearchVo;
import sustech.hotel.room.entity.RoomTypeEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RoomTypeService extends IService<RoomTypeEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<AvailableRoomTypeTo> getAvailableRoomType(Long hotelId, List<Long> conflictList);

    RoomTypeSearchVo search(Long hotelId, Integer guests, String startDate, String endDate, String sortBy, Boolean reversed, BigDecimal lowest, BigDecimal highest, Boolean breakfast, Boolean windows, Boolean television, Boolean bathtub, Boolean thermos);
}