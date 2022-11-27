package sustech.hotel.model.vo.hotel;

import lombok.Data;

import java.util.List;

@Data
public class SearchRespVo {
    private List<LocationVo> locations;
    private List<HotelVo> hotels;
    private Boolean isLogin;
}
