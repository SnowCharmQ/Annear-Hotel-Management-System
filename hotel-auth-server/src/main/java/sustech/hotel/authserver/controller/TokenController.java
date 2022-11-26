package sustech.hotel.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.JwtHelper;
import sustech.hotel.model.vo.member.TokenVo;

@Controller
public class TokenController {

    @ResponseBody
    @GetMapping("/getUsernameByToken")
    public JsonResult<String> getUsernameByToken(String token){
        System.out.println("hi");
        System.out.println(token);
        return new JsonResult<String>(JwtHelper.getUsername(token));
    }
}
