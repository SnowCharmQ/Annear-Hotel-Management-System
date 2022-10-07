package sustech.hotel.member.service.impl;

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
import sustech.hotel.model.vo.member.UserRegisterVo;


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
        UserInfoEntity entity = new UserInfoEntity();
        UserInfoEntity one
                = this.baseMapper.selectOne(new QueryWrapper<UserInfoEntity>()
                .eq("username", vo.getUsername()));
        if (one != null) {
            return new JsonResult<>(ExceptionCodeEnum.USERNAME_EXISTED_EXCEPTION.getCode(),
                    ExceptionCodeEnum.USERNAME_EXISTED_EXCEPTION.getMessage());
        }
        entity.setUsername(vo.getUsername());
        entity.setPhone(vo.getPhone());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        entity.setPassword(encode);
        BigDecimal balance = new BigDecimal('0');
        entity.setBalance(balance);
        this.baseMapper.insert(entity);
        return new JsonResult<>();
    }
}