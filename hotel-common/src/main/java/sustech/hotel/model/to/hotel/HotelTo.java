package sustech.hotel.model.to.hotel;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HotelTo {
    private Long hotelId;
    private String province;
    private String city;
    private String district;
    private String hotelName;
    private String detailAddress;
    private String email;
    private String description;
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
}
