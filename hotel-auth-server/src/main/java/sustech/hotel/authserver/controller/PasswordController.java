package sustech.hotel.authserver.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sustech.hotel.authserver.feign.MemberFeignService;
import sustech.hotel.authserver.feign.ThirdpartyFeignService;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.constant.AuthConstant;
import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.SmsCodeHighFrequencyException;
import sustech.hotel.exception.auth.SmsCodeIncorrectException;
import sustech.hotel.model.vo.member.ModifyPasswordVo;
import sustech.hotel.model.vo.member.PhoneVo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static sustech.hotel.common.utils.Constant.OK;

@Controller
public class PasswordController {

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    ThirdpartyFeignService thirdpartyFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @ResponseBody
    @GetMapping("/message/pwd")
    public JsonResult<Void> message(PhoneVo vo) {
        String redisCode = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_PWD_PREFIX + vo.getPhone());
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
        JsonResult<Void> result = thirdpartyFeignService.sendCode(vo.getPhone(), String.valueOf(code));
        if (result.getState() != OK) {
            return result;
        }
        code.append("_").append(System.currentTimeMillis());
        //验证码有效期三分钟
        redisTemplate.opsForValue().set(AuthConstant.SMS_CODE_CACHE_PWD_PREFIX + vo.getPhone(),
                String.valueOf(code), 3, TimeUnit.MINUTES);
        return result;
    }

    @ResponseBody
    @PostMapping("/modifypwd")
    public JsonResult<Void> modifyPassword(@RequestBody ModifyPasswordVo vo) {
        String code = vo.getCode();
        String redisCode = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_PWD_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(redisCode)) {
            if (code.equals(redisCode.substring(0, 6))) {
                redisTemplate.delete(AuthConstant.SMS_CODE_CACHE_PWD_PREFIX + vo.getPhone());
                return memberFeignService.modifyPassword(vo);
            }
        }
        return new JsonResult<>(new SmsCodeIncorrectException(ExceptionCodeEnum.SMS_CODE_INCORRECT_EXCEPTION));
    }
}