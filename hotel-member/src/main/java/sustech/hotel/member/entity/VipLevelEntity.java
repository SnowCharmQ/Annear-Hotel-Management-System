package sustech.hotel.member.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
@TableName("chm_vip_level")
public class VipLevelEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer vipLevel;
    private String vipName;
    private Long growthNeeded;
}