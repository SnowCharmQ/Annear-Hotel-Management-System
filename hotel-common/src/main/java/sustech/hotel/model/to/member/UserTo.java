package sustech.hotel.model.to.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTo {
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
