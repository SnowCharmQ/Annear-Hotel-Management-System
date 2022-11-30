package sustech.hotel.room.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.JwtHelper;
import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.PasswordIncorrectException;
import sustech.hotel.room.entity.AdminEntity;
import sustech.hotel.room.entity.HotelEntity;
import sustech.hotel.room.service.AdminService;
import sustech.hotel.room.service.HotelService;


@RestController
@RequestMapping("room/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private HotelService hotelService;

    @ResponseBody
    @GetMapping("/login")
    public JsonResult<String> login(@RequestParam("hotelId") Long hotelId, @RequestParam("pwd") String pwd) {
        AdminEntity one = adminService.getOne(new QueryWrapper<AdminEntity>().and(o -> o.eq("hotel_id", hotelId).eq("password", pwd)));
        if (one == null) return new JsonResult<>(new PasswordIncorrectException(ExceptionCodeEnum.PASSWORD_INCORRECT_EXCEPTION));
        else {
            HotelEntity hotel = hotelService.getById(hotelId);
            return new JsonResult<>(JwtHelper.createToken(hotelId, hotel.getHotelName()));
        }
    }
}