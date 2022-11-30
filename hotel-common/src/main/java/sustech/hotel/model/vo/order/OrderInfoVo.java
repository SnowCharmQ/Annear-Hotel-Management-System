package sustech.hotel.model.vo.order;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class OrderInfoVo {
    private String orderId;
    private Long userId;
    private Long typeId;
    private Long roomId;
    private Integer orderStatus;
    private Date startDate;
    private Date endDate;
    private BigDecimal originMoney;
    private BigDecimal afterDiscount;
    private String additional;
    private Integer score;
    private Long hotelId;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private Long roomNumber;
    private String hotelName;
}
