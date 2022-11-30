package sustech.hotel.model.vo.hotel;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@ApiModel("酒店地图信息")
public class HotelMapVo {
    private Long hotelId;
    private String province;
    private String city;
    private String district;
    private String hotelName;
    private String detailAddress;
    private BigDecimal price;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String picture;

}
