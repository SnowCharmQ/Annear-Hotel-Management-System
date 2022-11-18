package sustech.hotel.model.vo.member;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class ModifyPasswordVo {
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "The format of the phone number is invalid")
    String phone;
    @Length(min = 6, max = 6, message = "The verification code must be 6 characters in length")
    String code;
    @Length(min = 6, max = 18, message = "The password must be 6 to 18 characters in length")
    String password;
}
