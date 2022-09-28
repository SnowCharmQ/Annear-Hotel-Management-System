package sustech.hotel.discount.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
@TableName("chd_own_coupon")
public class OwnCouponEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Integer couponId;
    private Integer useStatus;
    private String orderId;
}