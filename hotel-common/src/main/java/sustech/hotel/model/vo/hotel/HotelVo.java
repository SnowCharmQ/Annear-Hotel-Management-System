package sustech.hotel.model.vo.hotel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("酒店信息")
public class HotelVo {
    private Long hotelId;
    private String province;
    private String city;
    private String district;
    private String hotelName;
    private String detailAddress;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String telephone;
    private Integer swimmingPool;
    private Integer gym;
    private Integer diningRoom;
    private Integer bar;
    private Integer parking;
    private Integer spa;
    private Integer chessRoom;
    private Long starLevel;
    private String hotelPicture;
    private BigDecimal averagePrice;
}
