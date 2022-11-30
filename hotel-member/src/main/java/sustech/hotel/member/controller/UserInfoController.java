package sustech.hotel.member.controller;

import java.text.ParseException;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.common.utils.JwtHelper;
import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.UserNotFoundException;
import sustech.hotel.member.dao.UserInfoDao;
import sustech.hotel.member.entity.UserInfoEntity;
import sustech.hotel.member.feign.OrderFeignService;
import sustech.hotel.member.service.UserInfoService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.member.*;

@Api("用户信息控制类")
@RestController
@RequestMapping("member/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OrderFeignService orderFeignService;

    @Autowired
    private UserInfoDao userInfoDao;

    @Operation(summary = "根据手机号验证码登录或注册（feign调用）")
    @PostMapping("/login/code")
    public JsonResult<UserRespVo> loginByCode(@RequestBody String phone) {
        try {
            UserRespVo userRespVo = userInfoService.loginByCode(phone);
            userRespVo.setToken(JwtHelper.createToken(userRespVo.getUserId(), userRespVo.getUsername()));
            return new JsonResult<>(userRespVo);
        } catch (BaseException e) {
            return new JsonResult<>(e);
        }
    }

    @Operation(summary = "根据手机号密码登录（feign调用）")
    @PostMapping("/login/password")
    public JsonResult<UserRespVo> loginByPassword(@RequestBody PasswordLoginVo vo) {
        try {
            UserRespVo userRespVo = userInfoService.loginByPassword(vo);
            userRespVo.setToken(JwtHelper.createToken(userRespVo.getUserId(), userRespVo.getUsername()));
            return new JsonResult<>(userRespVo);
        } catch (BaseException e) {
            return new JsonResult<>(e);
        }
    }

    @ResponseBody
    @GetMapping("/avatar")
    public JsonResult<String> getAvatar(TokenVo vo) {
        if (vo.getToken() == null || StringUtils.isEmpty(vo.getToken()) || Objects.equals(vo.getToken(), "null")) {
            return new JsonResult<>(new UserNotFoundException(ExceptionCodeEnum.USER_NOT_FOUND_EXCEPTION));
        }
        Long userId = JwtHelper.getUserId(vo.getToken());
        UserInfoEntity user = userInfoService.getById(userId);
        if (user == null) {
            return new JsonResult<>(new UserNotFoundException(ExceptionCodeEnum.USER_NOT_FOUND_EXCEPTION));
        }
        return new JsonResult<>(user.getAvatar());
    }

    @PostMapping("/modify/password")
    public JsonResult<Void> modifyPassword(@RequestBody ModifyPasswordVo vo) {
        try {
            userInfoService.modifyPassword(vo);
            return new JsonResult<>();
        }catch (BaseException e) {
            return new JsonResult<>(e);
        }
    }

    /**
     * register user by detailed information
     */
    @RequestMapping("/register")
    public JsonResult<Void> register(@RequestBody UserRegisterVo vo) {
        try {
            userInfoService.register(vo);
            return new JsonResult<>();
        } catch (BaseException e) {
            return new JsonResult<>(e);
        } catch (ParseException e) {
            return new JsonResult<>(new BaseException(e.getMessage()));
        }
    }

    /**
     * @param phone 手机号
     * @return 根据手机号查询是否已经注册过
     */
    @Operation(summary = "根据手机号查询（feign调用）")
    @GetMapping("/phone")
    public JsonResult<Boolean> queryByPhone(@RequestBody String phone) {
        QueryWrapper<UserInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        UserInfoEntity userInfo = userInfoService.getOne(wrapper);
        return new JsonResult<>(userInfo != null);
    }

    /**
     * 根据传入的参数map进行分页查询
     */
    @RequestMapping("/list")
    public JsonResult<PageUtils> list(@RequestParam Map<String, Object> params) {
        PageUtils page = userInfoService.queryPage(params);
        return new JsonResult<>(page);
    }


    /**
     * 查找数据表中的一条数据（根据主键查找）
     */
    @RequestMapping("/info/{userId}")
    public JsonResult<UserInfoEntity> info(@PathVariable("userId") Long userId) {
        UserInfoEntity userInfo = userInfoService.getById(userId);
        return new JsonResult<>(userInfo);
    }

    /**
     * 保存一条数据到数据库中
     */
    @RequestMapping("/save")
    public JsonResult<Void> save(@RequestBody UserInfoEntity userInfo) {
        userInfoService.save(userInfo);
        return new JsonResult<>();
    }

    /**
     * 修改数据库中的一条数据（根据传入的一条类数据）
     */
    @RequestMapping("/update")
    public JsonResult<Void> update(@RequestBody UserInfoEntity userInfo) {
        userInfoService.updateById(userInfo);
        return new JsonResult<>();
    }

    /**
     * 批量删除数据库中的数据（根据主键删除）
     */
    @RequestMapping("/delete")
    public JsonResult<Void> delete(@RequestBody Long[] userIds) {
        userInfoService.removeByIds(Arrays.asList(userIds));
        return new JsonResult<>();
    }

    /**
     * query user info by id
     */
    @GetMapping("/queryUserInfoById")
    public JsonResult<UserInfoEntity> queryUserInfoById(String token) {
        Long userId = JwtHelper.getUserId(token);
        return new JsonResult<>(userInfoService.queryUserInfoById(userId));
    }

    /**
     * query user info by name
     */
    @ResponseBody
    @GetMapping("/queryUserInfoByName")
    public JsonResult<UserInfoEntity> queryUserInfoByName(String userName) {
        return new JsonResult<>(userInfoService.queryUserInfoByName(userName));
    }


    /**
     * query user's order by id
     */
    @GetMapping("/queryOrdersByUserId")
    public JsonResult<List<OrderTo>> queryOrdersByUserId(Long userId) {
        return orderFeignService.queryOrderByUser(userId);
    }

    @GetMapping("/alterUserInfo")
    public JsonResult<Void> alterUserInfo(String toEditId, String phone, String email, Integer gender, Long birthday, String province, String city, String detailAddress,String socialName) {
        Long userId = JwtHelper.getUserId(toEditId);
        System.out.println(toEditId);
        System.out.println(phone);
        System.out.println(birthday);
        Date day = new Date(birthday);
        userInfoDao.updateInfo(userId, phone, email, gender, day, province, city, detailAddress, socialName);
        return new JsonResult<>();
    }
}