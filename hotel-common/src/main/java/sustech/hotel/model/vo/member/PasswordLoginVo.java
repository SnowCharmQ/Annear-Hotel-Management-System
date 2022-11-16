package sustech.hotel.model.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
@ApiModel("手机号密码登录VO类")
public class PasswordLoginVo {

    @ApiModelProperty("手机号")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "The format of the phone number is invalid")
    private String phone;

    @ApiModelProperty("密码")
    @Length(min = 6, max = 18, message = "The password must be 6 to 18 characters in length")
    private String password;

}
