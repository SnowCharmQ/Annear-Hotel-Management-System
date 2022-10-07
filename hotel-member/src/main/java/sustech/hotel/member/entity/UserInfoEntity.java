package sustech.hotel.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;

@Data
@TableName("chm_user_info")
public class UserInfoEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Long userId;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String avatar;
    private Integer gender;
    private Date birthday;
    private String province;
    private String city;
    private String detailAddress;
    private String job;
    private Integer isBlocked;
    private String socialName;
    private String accessToken;
    private Long growth;
    private Date createTime;
    private BigDecimal balance;
    private Integer vipLevel;
    private String identityCard;
    private BigDecimal consumeAmount;
    private BigDecimal couponAmount;
    private Long orderCount;
    private Long commentCount;
    private Long loginCount;
}