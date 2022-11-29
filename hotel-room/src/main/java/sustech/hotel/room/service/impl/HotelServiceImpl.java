package sustech.hotel.room.service.impl;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.*;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.order.HotelNotFoundException;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.model.to.member.UserTo;
import sustech.hotel.model.vo.hotel.*;
import sustech.hotel.room.dao.HotelDao;
import sustech.hotel.room.dao.RoomTypeDao;
import sustech.hotel.room.entity.HotelEntity;
import sustech.hotel.room.entity.HotelPictureEntity;
import sustech.hotel.room.entity.RoomTypePictureEntity;
import sustech.hotel.room.feign.MemberFeignService;
import sustech.hotel.room.feign.OrderFeignService;
import sustech.hotel.room.service.HotelPictureService;
import sustech.hotel.room.service.HotelService;
import sustech.hotel.room.service.RoomTypePictureService;
import sustech.hotel.room.service.RoomTypeService;


@Service("hotelService")
public class HotelServiceImpl extends ServiceImpl<HotelDao, HotelEntity> implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomTypeDao roomTypeDao;

    @Autowired
    private HotelPictureService hotelPictureService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomTypePictureService roomTypePictureService;

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private OrderFeignService orderFeignService;

    @Autowired
    private ThreadPoolExecutor executor;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HotelEntity> page = this.page(
                new Query<HotelEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public SearchRespVo initSearch(String token) {
        SearchRespVo respVo = new SearchRespVo();
        Long userId = JwtHelper.getUserId(token);
        List<Long> collectHotels = new ArrayList<>();
        if (userId == null) {
            respVo.setIsLogin(false);
        } else {
            CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
                JsonResult<UserTo> user = memberFeignService.getUser(userId);
                respVo.setIsLogin(user != null && user.getData() != null);
            }, executor);
            CompletableFuture<List<Long>> task2 = CompletableFuture.supplyAsync(() -> {
                JsonResult<List<Long>> result = memberFeignService.showCollectedHotel(userId);
                return result.getData();
            }, executor);
            CompletableFuture.allOf(task1, task2).join();
            if (respVo.getIsLogin()) {
                collectHotels = task2.join();
            }
        }
        List<LocationVo> locations = hotelDao.selectAllLocations();
        List<HotelEntity> hotelEntities = this.list();
        List<Long> finalCollectHotels = collectHotels;
        List<HotelVo> hotelVos = hotelEntities.stream().map(o -> {
            HotelVo hotelVo = new HotelVo();
            BeanUtils.copyProperties(o, hotelVo);
            CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
                BigDecimal avgPrice = roomTypeDao.selectAvgPriceByHotelId(o.getHotelId());
                hotelVo.setAveragePrice(avgPrice);
            }, executor);
            CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
                String picturePath = hotelPictureService.getOne(new QueryWrapper<HotelPictureEntity>()
                        .and(i -> i.eq("hotel_id", o.getHotelId()).eq("cover", 1))).getPicturePath();
                hotelVo.setHotelPicture(picturePath);
            }, executor);
            CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
                hotelVo.setIsCollect(finalCollectHotels.contains(o.getHotelId()));
            }, executor);
            CompletableFuture.allOf(task1, task2, task3).join();
            return hotelVo;
        }).toList();
        respVo.setHotels(hotelVos);
        respVo.setLocations(locations);
        return respVo;
    }

    @Override
    public SearchRespVo searchHotel(String token, String sortBy, Boolean reversed,
                                    Boolean diningRoom, Boolean parking, Boolean spa,
                                    Boolean bar, Boolean gym, Boolean chessRoom, Boolean swimmingPool,
                                    BigDecimal lowest, BigDecimal highest, String location) {
        SearchRespVo respVo = this.initSearch(token);
        List<HotelVo> hotels = respVo.getHotels();
        String[] strings = location.split("/");
        List<HotelVo> vos = new ArrayList<>(hotels.stream().filter(o -> {
            boolean flag = true;
            if (diningRoom) flag = o.getDiningRoom() == 1;
            if (parking) flag = flag && o.getParking() == 1;
            if (spa) flag = flag && o.getSpa() == 1;
            if (bar) flag = flag && o.getBar() == 1;
            if (gym) flag = flag && o.getGym() == 1;
            if (chessRoom) flag = flag && o.getChessRoom() == 1;
            if (swimmingPool) flag = flag && o.getSwimmingPool() == 1;
            if (!Objects.equals(location, "")) {
                flag = flag && Objects.equals(strings[0], o.getProvince()) && Objects.equals(strings[1], o.getCity());
            }
            return flag && o.getAveragePrice().compareTo(lowest) > 0 && o.getAveragePrice().compareTo(highest) < 0;
        }).toList());
        if (!reversed){
            if (Objects.equals(sortBy, "name")) {
                vos.sort(Comparator.comparing(HotelVo::getHotelName));
            } else if (Objects.equals(sortBy, "price")) {
                vos.sort(Comparator.comparing(HotelVo::getAveragePrice));
            }
        } else {
            if (Objects.equals(sortBy, "name")) {
                vos.sort(Comparator.comparing(HotelVo::getHotelName).reversed());
            } else if (Objects.equals(sortBy, "price")) {
                vos.sort(Comparator.comparing(HotelVo::getAveragePrice).reversed());
            } else {
                Collections.reverse(vos);
            }
        }
        respVo.setHotels(vos);
        return respVo;
    }

    @Override
    public ReserveRespVo initReserve(ReserveReqVo vo) {
        ReserveRespVo resp = new ReserveRespVo();
        HotelEntity entity = this.getById(vo.getHotelId());
        if (entity == null) {
            throw new HotelNotFoundException(ExceptionCodeEnum.HOTEL_NOT_FOUND_EXCEPTION);
        }
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            String today = vo.getToday();
            String tomorrow = vo.getTomorrow();
            String data = orderFeignService.getConflictList(today, tomorrow, vo.getHotelId()).getData();
            List<Long> conflictList = JSON.parseArray(data, Long.class);
            List<AvailableRoomTypeTo> availableRoomTypes = roomTypeService.getAvailableRoomType(vo.getHotelId(), conflictList);
            Map<Long, List<String>> map = new HashMap<>();
            for (AvailableRoomTypeTo to : availableRoomTypes) {
                Long typeId = to.getTypeId();
                List<RoomTypePictureEntity> pictures = roomTypePictureService.list(new QueryWrapper<RoomTypePictureEntity>().eq("type_id", typeId));
                List<String> paths = pictures.stream().map(RoomTypePictureEntity::getPicturePath).toList();
                map.put(typeId, paths);
            }
            String toStr = orderFeignService.getAverageScore(JSON.toJSONString(availableRoomTypes)).getData();
            List<AvailableRoomTypeVo> vos = JSON.parseArray(toStr, AvailableRoomTypeVo.class);
            resp.setRoomTypes(vos);
            resp.setRoomTypeImages(map);
        }, executor);
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            List<HotelPictureEntity> pictures
                    = hotelPictureService.list(new QueryWrapper<HotelPictureEntity>().eq("hotel_id", vo.getHotelId()));
            List<String> images = pictures.stream().map(HotelPictureEntity::getPicturePath).toList();
            resp.setImages(images);
            BeanUtils.copyProperties(entity, resp);
        }, executor);
        CompletableFuture.allOf(task1, task2).join();
        return resp;
    }
}