package sustech.hotel.model.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("手机号密码登录VO类")
public class PasswordLoginVo {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("密码")
    private String password;
}
