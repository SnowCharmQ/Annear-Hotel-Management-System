package sustech.hotel.discount.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;

@Data
@TableName("chd_seckill")
public class SeckillEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer seckillId;
    private Integer typeId;
    private BigDecimal reduction;
    private String seckillName;
    private Date enableStartTime;
    private Date enableEndTime;
    private Date useStartTime;
    private Date useEndTime;
}