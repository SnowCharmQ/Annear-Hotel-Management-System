package sustech.hotel.model.to.hotel;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomInfoTo {
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
    private Long roomId;
    private Long roomNumber;
    private Long typeId;
    private Long layoutId;
    private Integer upperLimit;
    private BigDecimal price;
    private Integer breakfast;
    private Integer windows;
    private Integer television;
    private Integer bathtub;
    private Integer thermos;
}
