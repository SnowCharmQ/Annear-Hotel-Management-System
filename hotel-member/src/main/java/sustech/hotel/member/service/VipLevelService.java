package sustech.hotel.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.member.entity.VipLevelEntity;

import java.util.Map;

public interface VipLevelService extends IService<VipLevelEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

