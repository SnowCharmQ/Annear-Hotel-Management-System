package sustech.hotel.room.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.room.dao.HotelPictureDao;
import sustech.hotel.room.entity.HotelPictureEntity;
import sustech.hotel.room.service.HotelPictureService;


@Service("hotelPictureService")
public class HotelPictureServiceImpl extends ServiceImpl<HotelPictureDao, HotelPictureEntity> implements HotelPictureService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HotelPictureEntity> page = this.page(
                new Query<HotelPictureEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

}