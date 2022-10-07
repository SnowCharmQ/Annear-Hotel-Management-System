package sustech.hotel.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;

@Data
@TableName("cho_order")
public class OrderEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String orderId;
    private Long roomId;
    private Integer orderStatus;
    private Date startTime;
    private Date endTime;
    private BigDecimal originMoney;
    private BigDecimal afterDiscount;
}