package sustech.hotel.room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.room.entity.AdminEntity;

import java.util.Map;

public interface AdminService extends IService<AdminEntity> {
    PageUtils queryPage(Map<String, Object> params);
}