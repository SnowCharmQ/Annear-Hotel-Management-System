package sustech.hotel.member.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.JsonResult;
import sustech.hotel.common.utils.PageUtils;
import sustech.hotel.common.utils.Query;

import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.member.dao.UserInfoDao;
import sustech.hotel.member.entity.UserInfoEntity;
import sustech.hotel.member.service.UserInfoService;
import sustech.hotel.model.vo.member.PasswordLoginVo;
import sustech.hotel.model.vo.member.UserRegisterVo;
import sustech.hotel.model.vo.member.UserRespVo;


@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserInfoEntity> page = this.page(
                new Query<UserInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public JsonResult<Void> register(UserRegisterVo vo) {
        //判断手机号是否已经被注册
        UserInfoEntity one
                = this.baseMapper.selectOne(new QueryWrapper<UserInfoEntity>()
                .eq("username", vo.getUsername()));
        if (one != null) {
            return new JsonResult<>(ExceptionCodeEnum.USERNAME_EXISTED_EXCEPTION.getCode(),
                    ExceptionCodeEnum.USERNAME_EXISTED_EXCEPTION.getMessage());
        }
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUsername(vo.getUsername());
        entity.setPhone(vo.getPhone());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        entity.setPassword(encode);
        BigDecimal balance = new BigDecimal('0');
        entity.setBalance(balance);
        //插入一条新的用户信息到数据库
        this.baseMapper.insert(entity);
        return new JsonResult<>();
    }

    @Override
    public JsonResult<UserRespVo> loginByPassword(PasswordLoginVo vo) {
        String phone = vo.getPhone();
        String password = vo.getPassword();
        //判断是否有这个手机号注册的用户
        UserInfoEntity entity = this.baseMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("phone", phone));
        if (entity == null) {
            return new JsonResult<>(ExceptionCodeEnum.NOT_REGISTER_EXCEPTION.getCode(),
                    ExceptionCodeEnum.NOT_REGISTER_EXCEPTION.getMessage());
        }
        //判断密码是否正确
        String pwdDb = entity.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(password, pwdDb);
        if (matches) {
            UserRespVo resp = new UserRespVo();
            BeanUtils.copyProperties(entity, resp);
            return new JsonResult<>(resp);
        } else {
            return new JsonResult<>(ExceptionCodeEnum.PASSWORD_INCORRECT_EXCEPTION.getCode(),
                    ExceptionCodeEnum.PASSWORD_INCORRECT_EXCEPTION.getMessage());
        }
    }

    @Override
    public JsonResult<UserRespVo> loginByCode(String phone) {
        QueryWrapper<UserInfoEntity> wrapper = new QueryWrapper<UserInfoEntity>().eq("phone", phone);
        UserInfoEntity entity = this.baseMapper.selectOne(wrapper);
        if (entity == null) {
            entity = new UserInfoEntity();
            entity.setPhone(phone);
            entity.setBalance(new BigDecimal('0'));
            this.baseMapper.insert(entity);
            //插进数据库会附上用户ID
            entity = this.baseMapper.selectOne(wrapper);
        }
        //拷贝信息并返回
        UserRespVo resp = new UserRespVo();
        BeanUtils.copyProperties(entity, resp);
        return new JsonResult<>(resp);
    }
}