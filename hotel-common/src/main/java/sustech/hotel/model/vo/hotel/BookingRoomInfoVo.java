package sustech.hotel.model.vo.hotel;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookingRoomInfoVo {
    private Long roomId;
    private Long hotelId;
    private Long roomNumber;
    private Long typeId;
    private Long layoutId;
    private Long floorPlanId;
    private Integer upperLimit;
    private BigDecimal price;
    private Integer breakfast;
    private Integer windows;
    private Integer television;
    private Integer bathtub;
    private Integer thermos;
    private String typeName;
    private String description;
    private BigDecimal area;
    private Boolean canReserve;
}
