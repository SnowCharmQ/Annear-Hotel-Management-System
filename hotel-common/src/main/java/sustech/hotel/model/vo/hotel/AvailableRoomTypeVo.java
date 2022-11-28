package sustech.hotel.model.vo.hotel;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvailableRoomTypeVo {
    private Long typeId;
    private Long hotelId;
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
    private Integer num;
    private Double averageScore;
}
