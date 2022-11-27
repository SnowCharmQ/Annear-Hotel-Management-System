package sustech.hotel.member.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.JwtHelper;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.order.UserNotLoginException;
import sustech.hotel.member.dao.CollectHotelDao;
import sustech.hotel.member.entity.CollectHotelEntity;
import sustech.hotel.member.service.CollectHotelService;


@Service("collectHotelService")
public class CollectHotelServiceImpl extends ServiceImpl<CollectHotelDao, CollectHotelEntity> implements CollectHotelService {
    @Autowired
    CollectHotelDao collectHotelDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectHotelEntity> page = this.page(
                new Query<CollectHotelEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public void collectHotelByUser(Long userId, Long hotelId) {
        collectHotelDao.collectHotelByUser(userId, hotelId);
    }

    @Override
    public void cancelCollectHotel(Long userId, Long hotelId) {
        collectHotelDao.cancelCollectHotel(userId, hotelId);
    }

    @Override
    public List<Long> showCollectedHotel(Long userId) {
        return collectHotelDao.showCollectedHotels(userId);
    }
}