package sustech.hotel.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.exception.BaseException;
import sustech.hotel.member.entity.UserInfoEntity;
import sustech.hotel.model.to.discount.DiscountTo;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.member.PasswordLoginVo;
import sustech.hotel.model.vo.member.UserRegisterVo;
import sustech.hotel.model.vo.member.UserRespVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserInfoService extends IService<UserInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void register(UserRegisterVo vo) throws BaseException;

    UserRespVo loginByPassword(PasswordLoginVo vo) throws BaseException;

    UserRespVo loginByCode(String phone);

    UserInfoEntity queryUserInfoById(Long userId);

    UserInfoEntity queryUserInfoByName(String username);

    void alterInfo(Long toEditId, String phone, String email, String avatar, Integer gender, Date birthday, String province, String city, String detailAddress, String job, Integer isBlocked, String socialName);

    JsonResult<List<OrderTo>> queryOrderByUser(Long userId);

    JsonResult<List<DiscountTo>> queryDiscountByUser();

    void collectHotel(Long userId, Integer hotelId);

    void updateTest(Long toEdit, String name);
}

