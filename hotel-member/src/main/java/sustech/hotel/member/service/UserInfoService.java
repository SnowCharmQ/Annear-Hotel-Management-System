package sustech.hotel.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.member.entity.UserInfoEntity;
import sustech.hotel.model.vo.member.CodeLoginVo;
import sustech.hotel.model.vo.member.PasswordLoginVo;
import sustech.hotel.model.vo.member.UserRegisterVo;
import sustech.hotel.model.vo.member.UserRespVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface UserInfoService extends IService<UserInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);

    JsonResult<Void> register(UserRegisterVo vo);

    JsonResult<UserRespVo> loginByPassword(PasswordLoginVo vo);

    JsonResult<UserRespVo> loginByCode(String phone);

    JsonResult<UserInfoEntity> queryUserInfoById(Long userId);

    JsonResult<UserInfoEntity> queryUserInfoByName(String username);

    JsonResult<UserInfoEntity> alterInfo(
            Long toEditId,
            String phone,
            String email,
            String avatar,
            Integer gender,
            Date birthday,
            String province,
            String city,
            String detailAddress,
            String job,
            Integer isBlocked,
            String socialName,
            String accessToken,
            Long growth,
            Date createTime,
            BigDecimal balance,
            Integer vipLevel,
            String identityCard,
            BigDecimal consumeAmount,
            BigDecimal couponAmount,
            Long orderCount,
            Long commentCount,
            Long loginCount
    );

    //order
    JsonResult<Void> queryOrderInfo();

    JsonResult<Void> querySales();

    JsonResult<Void> queryHotels();
}

