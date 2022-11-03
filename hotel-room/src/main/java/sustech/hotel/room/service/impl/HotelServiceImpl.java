package sustech.hotel.room.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.model.vo.hotel.HotelVo;
import sustech.hotel.room.dao.HotelDao;
import sustech.hotel.room.entity.HotelEntity;
import sustech.hotel.room.service.HotelService;


@Service("hotelService")
public class HotelServiceImpl extends ServiceImpl<HotelDao, HotelEntity> implements HotelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HotelEntity> page = this.page(
                new Query<HotelEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    public HotelEntity convertHotelRequest(HotelVo hotelVo){
        HotelEntity entity = new HotelEntity();
        BeanUtils.copyProperties(hotelVo, entity);
        return entity;
    }
    public HotelVo convertHotel(HotelEntity hotelEntity){
        HotelVo hotelVo = new HotelVo();
        BeanUtils.copyProperties(hotelEntity, hotelVo);
        return hotelVo;
    }

}