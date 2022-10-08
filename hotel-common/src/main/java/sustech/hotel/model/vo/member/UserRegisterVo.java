package sustech.hotel.model.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("注册封装VO类")
public class UserRegisterVo {
    @ApiModelProperty("用户名")
    @Length(min = 3, max = 18, message = "用户名长度必须是3-18位字符")
    private String username;

    @ApiModelProperty("用户密码")
    @Length(min = 6, max = 18, message = "密码长度必须是6-18位字符")
    private String password;

    @ApiModelProperty("手机号")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty("验证码")
    @NotEmpty(message = "验证码必须提交")
    private String code;
}
