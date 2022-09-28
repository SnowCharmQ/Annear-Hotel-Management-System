package sustech.hotel.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
@TableName("cho_order_info")
public class OrderInfoEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String orderId;
    private String tenantName;
    private String identityCard;
    private String telephone;
}