package sustech.hotel.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sustech.hotel.member.entity.UserInfoEntity;
import sustech.hotel.member.service.UserInfoService;
import sustech.hotel.model.vo.member.UserRegisterVo;

import java.util.Date;

@SpringBootTest
class HotelMemberApplicationTests {
    @Autowired
    UserInfoService userInfoService;
    @Test
    void contextLoads() {
        UserRegisterVo vo = new UserRegisterVo();
//        userInfoService.register(vo);
//        UserInfoEntity entity = userInfoService.queryUserInfoById((long)12012705);
//        UserInfoEntity entity = userInfoService.queryUserInfoByName("Lijiachen");
//        userInfoService.alterInfo((long)12012705, "1111", "10086@com", "???" , 1, null, "hubei", "wuhan", "sustech", "student", 1, "ababa");
        System.out.println(userInfoService.queryOrderByUser((long)12012705));
//        userInfoService.updateTest((long)12012705, "Jiachang");
//        System.out.println(entity);
    }

}
