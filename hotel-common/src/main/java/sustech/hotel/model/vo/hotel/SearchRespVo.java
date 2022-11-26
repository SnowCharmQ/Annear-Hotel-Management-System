package sustech.hotel.model.vo.hotel;

import lombok.Data;

import java.util.List;

@Data
public class SearchRespVo {
    List<String> provinces;
    List<HotelVo> hotels;
}
