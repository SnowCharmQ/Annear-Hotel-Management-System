package sustech.hotel.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;

@Data
@TableName("cho_income_refund")
public class IncomeRefundEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String orderId;
    private BigDecimal income;
    private Date time;
}