package sustech.hotel.member.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.vo.hotel.HotelVo;

import java.util.List;

@FeignClient("hotel-room")
public interface RoomFeignService {

    @PostMapping("/room/hotel/collectedList")
    JsonResult<List<HotelVo>> getCollectedList(List<Long> hotelId);
}
