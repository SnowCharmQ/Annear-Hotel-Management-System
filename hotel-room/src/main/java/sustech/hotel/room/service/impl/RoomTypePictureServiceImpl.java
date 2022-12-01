package sustech.hotel.room.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.model.vo.hotel.RoomTypePictureVo;
import sustech.hotel.room.dao.RoomTypePictureDao;
import sustech.hotel.room.entity.RoomTypeEntity;
import sustech.hotel.room.entity.RoomTypePictureEntity;
import sustech.hotel.room.service.RoomTypePictureService;


@Service("roomTypePictureService")
public class RoomTypePictureServiceImpl extends ServiceImpl<RoomTypePictureDao, RoomTypePictureEntity> implements RoomTypePictureService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoomTypePictureEntity> page = this.page(
                new Query<RoomTypePictureEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public String getRoomPicture(Long typeId) {
        System.out.println(typeId);
        RoomTypePictureEntity entity = this.baseMapper.selectOne(new QueryWrapper<RoomTypePictureEntity>().eq("type_id", typeId).eq("cover", 1));
        if (entity == null) {
            return null;
        }
        else return entity.getPicturePath();
    }

    @Override
    public void addRoomPicture(RoomTypePictureVo entity) {
        RoomTypePictureEntity e = new RoomTypePictureEntity();
        BeanUtils.copyProperties(entity, e);
        this.baseMapper.insert(e);
    }

    @Override
    public void deleteRoomPicture(Long typeId) {
        this.remove(new QueryWrapper<RoomTypePictureEntity>().eq("type_id", typeId));
    }

}