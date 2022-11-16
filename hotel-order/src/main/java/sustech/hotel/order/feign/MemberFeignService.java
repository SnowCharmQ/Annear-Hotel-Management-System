package sustech.hotel.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.member.UserTo;

@FeignClient("hotel-member")
public interface MemberFeignService {
    @RequestMapping("member/userinfo/info/{userId}")
    JsonResult<UserTo> getUser(@PathVariable("userId") Long userId);
}
