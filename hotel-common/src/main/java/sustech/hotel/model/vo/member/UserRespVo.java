package sustech.hotel.model.vo.member;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRespVo {
    private Long userId;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String avatar;
    private String token;
}
