package sustech.hotel.room.service.impl;

import com.alibaba.fastjson2.JSON;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.room.RoomConflictsException;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;
import sustech.hotel.model.to.hotel.CommentInfoTo;
import sustech.hotel.model.to.hotel.RoomTypeTo;
import sustech.hotel.model.vo.hotel.AvailableRoomTypeVo;
import sustech.hotel.model.vo.hotel.RoomTypeSearchVo;
import sustech.hotel.model.vo.order.CommentVo;
import sustech.hotel.room.dao.RoomTypeDao;
import sustech.hotel.room.entity.HotelEntity;
import sustech.hotel.room.entity.RoomTypeEntity;
import sustech.hotel.room.entity.RoomTypePictureEntity;
import sustech.hotel.room.feign.OrderFeignService;
import sustech.hotel.room.service.HotelService;
import sustech.hotel.room.service.RoomService;
import sustech.hotel.room.service.RoomTypePictureService;
import sustech.hotel.room.service.RoomTypeService;


@Service("roomTypeService")
public class RoomTypeServiceImpl extends ServiceImpl<RoomTypeDao, RoomTypeEntity> implements RoomTypeService {

    @Autowired
    private OrderFeignService orderFeignService;

    @Autowired
    private RoomTypePictureService roomTypePictureService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    ThreadPoolExecutor executor;

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

    @Override
    public RoomTypeSearchVo search(Long hotelId, Integer guests, String startDate, String endDate,
                                   String sortBy, Boolean reversed, BigDecimal lowest, BigDecimal highest,
                                   Boolean breakfast, Boolean windows, Boolean television, Boolean bathtub,
                                   Boolean thermos) {
        RoomTypeSearchVo resp = new RoomTypeSearchVo();
        String data = orderFeignService.getConflictList(startDate, endDate, hotelId).getData();
        List<Long> conflictList = JSON.parseArray(data, Long.class);
        List<AvailableRoomTypeTo> availableRoomTypes = this.getAvailableRoomType(hotelId, conflictList);
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            String json = JSON.toJSONString(availableRoomTypes);
            String result = orderFeignService.getAverageScore(json).getData();
            List<AvailableRoomTypeVo> vos = JSON.parseArray(result, AvailableRoomTypeVo.class);
            vos = vos.stream().filter(o -> {
                boolean flag = true;
                if (breakfast) flag = o.getBreakfast() == 1;
                if (windows) flag = flag && o.getWindows() == 1;
                if (television) flag = flag && o.getTelevision() == 1;
                if (bathtub) flag = flag && o.getBathtub() == 1;
                if (thermos) flag = flag && o.getThermos() == 1;
                return flag && o.getPrice().compareTo(lowest) > 0 && o.getPrice().compareTo(highest) < 0
                        && o.getUpperLimit() >= guests;
            }).toList();
            vos = new ArrayList<>(vos);
            if (!reversed) {
                if (Objects.equals(sortBy, "score")) {
                    vos.sort(Comparator.comparing(AvailableRoomTypeVo::getAverageScore));
                } else if (Objects.equals(sortBy, "price")) {
                    vos.sort(Comparator.comparing(AvailableRoomTypeVo::getPrice));
                }
            } else {
                if (Objects.equals(sortBy, "score")) {
                    vos.sort(Comparator.comparing(AvailableRoomTypeVo::getAverageScore).reversed());
                } else if (Objects.equals(sortBy, "price")) {
                    vos.sort(Comparator.comparing(AvailableRoomTypeVo::getPrice).reversed());
                }
            }
            resp.setRoomTypes(vos);
        }, executor);
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
            Map<Long, List<String>> map = new HashMap<>();
            for (AvailableRoomTypeTo to : availableRoomTypes) {
                Long typeId = to.getTypeId();
                List<RoomTypePictureEntity> pictures = roomTypePictureService.list(new QueryWrapper<RoomTypePictureEntity>().eq("type_id", typeId));
                List<String> paths = pictures.stream().map(RoomTypePictureEntity::getPicturePath).toList();
                map.put(typeId, paths);
            }
            resp.setRoomTypeImages(map);
        }, executor);
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
            String s = orderFeignService.getAllComments().getData();
            List<CommentVo> comments = JSON.parseArray(s, CommentVo.class);
            Map<Long, List<CommentVo>> map = new HashMap<>();
            for (CommentVo comment : comments) {
                Long typeId = comment.getTypeId();
                if (map.containsKey(typeId)) {
                    List<CommentVo> vos = map.get(typeId);
                    vos.add(comment);
                } else {
                    List<CommentVo> vos = new ArrayList<>();
                    vos.add(comment);
                    map.put(typeId, vos);
                }
            }
            resp.setComments(map);
        }, executor);
        CompletableFuture.allOf(task1, task2, task3).join();
        return resp;
    }

    @Override
    public CommentInfoTo getCommentInfo(Long typeId) {
        CommentInfoTo to = new CommentInfoTo();
        RoomTypeEntity roomType = this.getById(typeId);
        Long hotelId = roomType.getHotelId();
        HotelEntity hotel = hotelService.getById(hotelId);
        to.setTypeId(typeId);
        to.setTypeName(roomType.getTypeName());
        to.setHotelId(hotelId);
        to.setHotelName(hotel.getHotelName());
        RoomTypePictureEntity picture = roomTypePictureService.getOne(new QueryWrapper<RoomTypePictureEntity>()
                .and(o -> o.eq("type_id", typeId).eq("cover", 1)));
        to.setTypePicture(picture.getPicturePath());
        return to;
    }

    @Override
    public void addRoomType(RoomTypeEntity entity) {
        this.baseMapper.insert(entity);
    }

    @Override
    public void deleteType(Long typeId) {
        if (roomService.typeExist(typeId)) {
            throw new RoomConflictsException(ExceptionCodeEnum.ROOM_CONFLICTS_EXCEPTION);
        } else {
            this.baseMapper.delete(new QueryWrapper<RoomTypeEntity>().eq("type_id", typeId));
        }
    }

    @Override
    public void alterType(RoomTypeEntity roomType) {
        this.updateById(roomType);
    }
}