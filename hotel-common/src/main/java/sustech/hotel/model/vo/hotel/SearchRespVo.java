package sustech.hotel.model.vo.hotel;

import lombok.Data;

import java.util.List;

@Data
public class SearchRespVo {
    List<LocationVo> locations;
    List<HotelVo> hotels;
}
