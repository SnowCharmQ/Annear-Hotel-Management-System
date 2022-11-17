package sustech.hotel.model.vo.member;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class UserRegisterVo {
    String username;

    @Length(min = 6, max = 18, message = "The password must be 6 to 18 characters in length")
    String password;

    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "The format of the phone number is invalid")
    String phone;

    @Pattern(regexp = "^[a-z0-9A-Z]+[- |a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-z]{2,}$", message = "The format of the email address is invalid")
    String email;

    Integer gender;

    String province;

    String city;

    String address;

    String socialName;

    Long birthday;
}
