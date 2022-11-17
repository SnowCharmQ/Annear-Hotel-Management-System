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
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "注册接口")
    @PostMapping("/register")
    @ResponseBody
    public JsonResult<Void> register(@RequestBody @Valid UserRegisterVo vo, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,
                    o -> Optional.ofNullable(o.getDefaultMessage()).orElse("")));
            return new JsonResult<>(ExceptionCodeEnum.INVALID_INPUT_EXCEPTION.getCode(),
                    ExceptionCodeEnum.INVALID_INPUT_EXCEPTION.getMessage(), errors);
        }
        return memberFeignService.register(vo);
    }
}
