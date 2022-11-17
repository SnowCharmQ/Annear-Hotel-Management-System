package sustech.hotel.model.vo.member;

import lombok.Data;

@Data
public class ModifyPasswordVo {
    String phone;
    String code;
    String password;
}
