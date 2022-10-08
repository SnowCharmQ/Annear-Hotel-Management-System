package sustech.hotel.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.member.entity.UserInfoEntity;
import sustech.hotel.model.vo.member.CodeLoginVo;
import sustech.hotel.model.vo.member.PasswordLoginVo;
import sustech.hotel.model.vo.member.UserRegisterVo;
import sustech.hotel.model.vo.member.UserRespVo;

import java.util.Map;

public interface UserInfoService extends IService<UserInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);

    JsonResult<Void> register(UserRegisterVo vo);

    JsonResult<UserRespVo> loginByPassword(PasswordLoginVo vo);

    JsonResult<UserRespVo> loginByCode(String phone);
}

