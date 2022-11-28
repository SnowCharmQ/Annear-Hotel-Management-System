package sustech.hotel.model.vo.hotel;

import lombok.Data;
import sustech.hotel.model.to.hotel.AvailableRoomTypeTo;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReserveRespVo {
    private Long hotelId;
    private String province;
    private String city;
    private String district;
    private String hotelName;
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
    private List<String> images;
    private List<AvailableRoomTypeTo> roomTypes;
}
