package sustech.hotel.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.math.BigDecimal;

import lombok.Data;

@Data
@TableName("cho_order")
public class OrderEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
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
}