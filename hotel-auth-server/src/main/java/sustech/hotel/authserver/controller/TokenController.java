package sustech.hotel.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.JwtHelper;

@Controller
public class TokenController {

    @ResponseBody
    @GetMapping("/getUsernameByToken")
    public JsonResult<String> getUsernameByToken(String token){
        return new JsonResult<String>(JwtHelper.getUsername(token));
    }
}
