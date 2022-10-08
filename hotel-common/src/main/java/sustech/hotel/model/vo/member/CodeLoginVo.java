package sustech.hotel.model.vo.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("手机号验证码登录VO类")
public class CodeLoginVo {
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("验证码")
    private String code;
}
