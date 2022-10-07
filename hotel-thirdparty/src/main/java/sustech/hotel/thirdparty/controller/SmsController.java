package sustech.hotel.thirdparty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.thirdparty.component.SmsComponent;

import static sustech.hotel.common.utils.Constant.OK;

@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    SmsComponent smsComponent;

    @GetMapping("/sendcode")
    public JsonResult<Void> sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        smsComponent.sendSmsCode(phone, code);
        return new JsonResult<>(OK);
    }
}
