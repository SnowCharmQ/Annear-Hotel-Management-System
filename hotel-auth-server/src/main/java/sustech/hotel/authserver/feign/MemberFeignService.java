package sustech.hotel.authserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.vo.member.UserRegisterVo;

@FeignClient("hotel-member")
public interface MemberFeignService {
    @GetMapping("/member/userinfo/info/phone")
    JsonResult<Boolean> queryByPhone(String phone);

    @GetMapping("/member/userinfo/register")
    JsonResult<Void> register(UserRegisterVo vo);
}
