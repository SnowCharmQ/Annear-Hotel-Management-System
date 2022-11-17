package sustech.hotel.authserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.vo.member.ModifyPasswordVo;
import sustech.hotel.model.vo.member.PasswordLoginVo;
import sustech.hotel.model.vo.member.UserRegisterVo;
import sustech.hotel.model.vo.member.UserRespVo;

@FeignClient("hotel-member")
public interface MemberFeignService {
    @GetMapping("/member/userinfo/phone")
    JsonResult<Boolean> queryByPhone(String phone);

    @PostMapping("/member/userinfo/register")
    JsonResult<Void> register(UserRegisterVo vo);

    @PostMapping("/member/userinfo/login/password")
    JsonResult<UserRespVo> loginByPassword(PasswordLoginVo vo);

    @PostMapping("/member/userinfo/login/code")
    JsonResult<UserRespVo> loginByCode(String phone);

    @PostMapping("/member/userinfo/modify/password")
    JsonResult<Void> modifyPassword(ModifyPasswordVo vo);
}
