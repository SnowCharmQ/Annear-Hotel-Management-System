package sustech.hotel.model.to.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderTo {
    private String orderId;
    private Long userId;
    private Long roomId;
    private Integer orderStatus;
    private Date startTime;
    private Date endTime;
    private BigDecimal originMoney;
    private BigDecimal afterDiscount;
}
