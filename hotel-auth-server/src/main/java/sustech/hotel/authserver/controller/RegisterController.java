package sustech.hotel.authserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sustech.hotel.authserver.feign.MemberFeignService;
import sustech.hotel.authserver.feign.ThirdpartyFeignService;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.constant.AuthConstant;
import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.SmsCodeHighFrequencyException;
import sustech.hotel.model.vo.member.UserRegisterVo;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static sustech.hotel.common.utils.Constant.OK;

@Api("注册接口类：发送短信、注册功能")
@Controller
public class RegisterController {

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    ThirdpartyFeignService thirdpartyFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Operation(summary = "发送注册短信验证码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = true)
    })
    @ResponseBody
    @GetMapping("/message/reg")
    public JsonResult<Void> message(@RequestParam("phone") String phone) {
        String redisCode = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_REG_PREFIX + phone);
        if (redisCode != null) {
            //设定发送短信间隔时长1min
            long l = Long.parseLong(redisCode.substring(7));
            if (System.currentTimeMillis() - l < 60000) {
                return new JsonResult<>(new SmsCodeHighFrequencyException(ExceptionCodeEnum.SMS_CODE_HIGH_FREQUENCY_EXCEPTION.getCode(),
                        ExceptionCodeEnum.SMS_CODE_HIGH_FREQUENCY_EXCEPTION.getMessage()));
            }
        }
        //随机生成验证码
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        //调用第三方服务发送短信
        JsonResult<Void> result = thirdpartyFeignService.sendCode(phone, String.valueOf(code));
        if (result.getState() != OK) {
            return result;
        }
        code.append("_").append(System.currentTimeMillis());
        //验证码有效期三分钟
        redisTemplate.opsForValue().set(AuthConstant.SMS_CODE_CACHE_REG_PREFIX + phone,
                String.valueOf(code), 3, TimeUnit.MINUTES);
        return new JsonResult<>();
    }


    @Operation(summary = "注册接口")
    @PostMapping("/register")
    public JsonResult<Map<String, String>> register(@Valid UserRegisterVo vo, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,
                    o -> Optional.ofNullable(o.getDefaultMessage()).orElse("")));
            return new JsonResult<>(errors);
        }
        JsonResult<Boolean> queryByPhone = memberFeignService.queryByPhone(vo.getPhone());
        if (queryByPhone.getData() == Boolean.TRUE) {
            Map<String, String> errors = new HashMap<>();
            errors.put("phone", "手机号已经被注册");
            return new JsonResult<>(errors);
        }
        String code = vo.getCode();
        String redisCode = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_REG_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(redisCode)) {
            if (code.equals(redisCode.substring(0, 6))) {
                redisTemplate.delete(AuthConstant.SMS_CODE_CACHE_REG_PREFIX + vo.getPhone());
                JsonResult<Void> jsonResult = memberFeignService.register(vo);
                if (jsonResult.getState() == OK) {
                    return new JsonResult<>(new HashMap<>());
                } else {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("user", jsonResult.getMessage());
                    return new JsonResult<>(errors);
                }
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("code", "验证码错误");
                return new JsonResult<>(errors);
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("code", "验证码错误");
            return new JsonResult<>(errors);
        }
    }
}
