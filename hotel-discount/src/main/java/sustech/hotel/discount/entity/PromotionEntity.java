package sustech.hotel.discount.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;

@Data
@TableName("chd_promotion")
public class PromotionEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer promotionId;
    private Integer typeId;
    private BigDecimal reduction;
    private String promotionName;
    private Date enableStartTime;
    private Date enableEndTime;
    private Date useStartTime;
    private Date useEndTime;
}