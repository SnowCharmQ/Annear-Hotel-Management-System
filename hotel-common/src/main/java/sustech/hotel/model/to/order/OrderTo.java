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

    public OrderTo(String orderId, Long userId, Long roomId, Integer orderStatus, Date startTime, Date endTime, BigDecimal originMoney, BigDecimal afterDiscount) {
        this.orderId = orderId;
        this.userId = userId;
        this.roomId = roomId;
        this.orderStatus = orderStatus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.originMoney = originMoney;
        this.afterDiscount = afterDiscount;
    }
}
