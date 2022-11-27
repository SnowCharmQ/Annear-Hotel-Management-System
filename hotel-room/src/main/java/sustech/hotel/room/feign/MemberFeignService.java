package sustech.hotel.room.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.member.UserTo;

import java.util.List;

@FeignClient("hotel-member")
public interface MemberFeignService {
    @RequestMapping("member/userinfo/info/{userId}")
    JsonResult<UserTo> getUser(@PathVariable("userId") Long userId);

    @RequestMapping("member/collecthotel/showCollectedHotel")
    JsonResult<List<Long>> showCollectedHotel(@RequestParam("userId") Long userId);
}
