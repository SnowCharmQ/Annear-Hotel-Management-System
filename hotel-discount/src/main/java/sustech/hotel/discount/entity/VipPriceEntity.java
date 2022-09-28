package sustech.hotel.discount.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
@TableName("chd_vip_price")
public class VipPriceEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer vipLevel;
    private Integer typeId;
    private BigDecimal price;
}