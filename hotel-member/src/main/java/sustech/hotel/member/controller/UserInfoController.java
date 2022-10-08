package sustech.hotel.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sustech.hotel.member.entity.UserInfoEntity;
import sustech.hotel.member.service.UserInfoService;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.model.vo.member.CodeLoginVo;
import sustech.hotel.model.vo.member.PasswordLoginVo;
import sustech.hotel.model.vo.member.UserRegisterVo;
import sustech.hotel.model.vo.member.UserRespVo;

@Api("用户信息控制类")
@RestController
@RequestMapping("member/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Operation(summary = "根据手机号验证码登录或注册（feign调用）")
    @PostMapping("/login/code")
    public JsonResult<UserRespVo> loginByCode(@RequestBody String phone) {
        return userInfoService.loginByCode(phone);
    }

    @Operation(summary = "根据手机号密码登录（feign调用）")
    @PostMapping("/login/password")
    public JsonResult<UserRespVo> loginByPassword(@RequestBody PasswordLoginVo vo) {
        return userInfoService.loginByPassword(vo);
    }

    /**
     * @param vo 注册VO类
     * @return 根据传入的用户名、密码、手机号完成用户注册
     */
    @Operation(summary = "注册（feign调用）")
    @PostMapping("/register")
    public JsonResult<Void> register(@RequestBody UserRegisterVo vo) {
        return userInfoService.register(vo);
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
}