package sustech.hotel.model.vo.order;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class OrderConfirmRespVo {
    private Long roomId;
    private Long hotelId;
    private Long roomNumber;
    private Long typeId;
    private Long layoutId;
    private Long floorPlanId;
    private Integer upperLimit;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer breakfast;
    private BigDecimal finalPrice;
    private String token;
    private Date startDate;
    private Date endDate;
}
