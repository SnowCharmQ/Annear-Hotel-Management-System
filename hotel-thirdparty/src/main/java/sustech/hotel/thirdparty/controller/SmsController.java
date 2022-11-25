package sustech.hotel.thirdparty.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.SmsCodeException;
import sustech.hotel.thirdparty.component.SmsComponent;


@Api("发送短信工具类")
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    SmsComponent smsComponent;

    /**
     * @param phone 手机号
     * @param code  验证码
     * @return 发送短信成功（暂未实现成功发送，未申请模板）
     * TODO：申请模板（需盖章）
     */
    @Operation(summary = "发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", required = true)
    })
    @GetMapping("/sendcode")
    public JsonResult<Void> sendCode(@RequestParam("phone") String phone,
                                     @RequestParam("code") String code) {
        try {
            smsComponent.sendSmsCode(phone, code);
        } catch (Exception e) {
            return new JsonResult<>(new SmsCodeException(ExceptionCodeEnum.SMS_CODE_EXCEPTION));
        }
        return new JsonResult<>();
    }
}
