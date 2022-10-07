package sustech.hotel.authserver.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sustech.hotel.authserver.feign.MemberFeignService;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.constant.AuthConstant;
import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.model.vo.member.UserRegisterVo;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static sustech.hotel.common.utils.Constant.OK;

@Controller
public class RegisterController {

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @ResponseBody
    @GetMapping("/message")
    public JsonResult<Void> message(@RequestParam("phone") String phone) {
        String redisCode = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (redisCode != null) {
            long l = Long.parseLong(redisCode.substring(7));
            if (System.currentTimeMillis() - l < 60000) {
                return new JsonResult<>(ExceptionCodeEnum.SMS_CODE_EXCEPTION.getCode(),
                        ExceptionCodeEnum.SMS_CODE_EXCEPTION.getMessage());
            }
        }
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        code.append("_").append(System.currentTimeMillis());
        redisTemplate.opsForValue().set(AuthConstant.SMS_CODE_CACHE_PREFIX + phone,
                String.valueOf(code), 5, TimeUnit.MINUTES);
        return new JsonResult<>(OK);
    }


    @PostMapping("/register")
    public JsonResult<Map<String, String>> register(@Valid UserRegisterVo vo, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,
                    DefaultMessageSourceResolvable::getDefaultMessage));
            return new JsonResult<>(errors);
        }
        JsonResult<Boolean> queryByPhone = memberFeignService.queryByPhone(vo.getPhone());
        if (queryByPhone.getData() == Boolean.TRUE) {
            Map<String, String> errors = new HashMap<>();
            errors.put("phone", "手机号已经被注册");
            return new JsonResult<>(errors);
        }
        String code = vo.getCode();
        String redisCode = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(redisCode)) {
            if (code.equals(redisCode.substring(0, 6))) {
                redisTemplate.delete(AuthConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                JsonResult<Void> jsonResult = memberFeignService.register(vo);
                if (jsonResult.getState() == OK){
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
