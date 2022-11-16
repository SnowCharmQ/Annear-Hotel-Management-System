package sustech.hotel.order.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("hotel-room")
public interface RoomFeignService {
}
