package sustech.hotel.member.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import sustech.hotel.common.utils.*;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;
import sustech.hotel.exception.auth.*;
import sustech.hotel.exception.order.UserNotLoginException;
import sustech.hotel.member.dao.UserInfoDao;
import sustech.hotel.member.entity.UserInfoEntity;
import sustech.hotel.member.feign.DiscountFeignService;
import sustech.hotel.member.feign.OrderFeignService;
import sustech.hotel.member.service.UserInfoService;
import sustech.hotel.model.to.discount.DiscountTo;
import sustech.hotel.model.to.order.OrderTo;
import sustech.hotel.model.vo.member.ModifyPasswordVo;
import sustech.hotel.model.vo.member.PasswordLoginVo;
import sustech.hotel.model.vo.member.UserRegisterVo;
import sustech.hotel.model.vo.member.UserRespVo;


@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private OrderFeignService orderFeignService;

    @Autowired
    private DiscountFeignService discountFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserInfoEntity> page = this.page(
                new Query<UserInfoEntity>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public void register(UserRegisterVo vo) throws BaseException, ParseException {
        UserInfoEntity one
                = this.baseMapper.selectOne(new QueryWrapper<UserInfoEntity>()
                .eq("phone", vo.getPhone()));
        if (one != null) {
            throw new PhoneNumberExistedException(ExceptionCodeEnum.PHONE_NUMBER_EXISTED_EXCEPTION);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday;
        if (vo.getBirthday() != null && vo.getBirthday().length() > 0) {
            birthday = sdf.parse(vo.getBirthday());
        } else {
            birthday = null;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUsername(vo.getUsername());
        entity.setPassword(encode);
        entity.setPhone(vo.getPhone());
        entity.setEmail(vo.getEmail());
        entity.setGender(vo.getGender());
        entity.setProvince(vo.getProvince());
        entity.setCity(vo.getCity());
        entity.setDetailAddress(vo.getAddress());
        entity.setSocialName(vo.getSocialName());
        entity.setBirthday(birthday);
        entity.setBalance(BigDecimal.ZERO);
        entity.setCreateTime(new Date());
        entity.setAvatar("https://ooad-1312953997.cos.ap-guangzhou.myqcloud.com/img/user-filling.png");
        this.baseMapper.insert(entity);
    }

    @Override
    public UserRespVo loginByPassword(PasswordLoginVo vo) throws BaseException {
        String phone = vo.getPhone();
        String password = vo.getPassword();
        //判断是否有这个手机号注册的用户
        UserInfoEntity entity = this.baseMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("phone", phone));
        if (entity == null) {
            throw new NotRegisterException(ExceptionCodeEnum.NOT_REGISTER_EXCEPTION);
        }
        //判断密码是否正确
        String pwdDb = entity.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(password, pwdDb);
        if (matches) {
            UserRespVo resp = new UserRespVo();
            BeanUtils.copyProperties(entity, resp);
            return resp;
        } else {
            throw new PasswordIncorrectException(ExceptionCodeEnum.PASSWORD_INCORRECT_EXCEPTION);
        }
    }

    @Override
    public UserRespVo loginByCode(String phone) {
        QueryWrapper<UserInfoEntity> wrapper = new QueryWrapper<UserInfoEntity>().eq("phone", phone);
        UserInfoEntity entity = this.baseMapper.selectOne(wrapper);
        if (entity == null) {
            entity = new UserInfoEntity();
            entity.setPhone(phone);
            entity.setBalance(BigDecimal.ZERO);
            entity.setCreateTime(new Date());
            entity.setAvatar("https://ooad-1312953997.cos.ap-guangzhou.myqcloud.com/img/user-filling.png");
            this.baseMapper.insert(entity);
            //插进数据库会附上用户ID
            entity = this.baseMapper.selectOne(wrapper);
        }
        //拷贝信息并返回
        UserRespVo resp = new UserRespVo();
        BeanUtils.copyProperties(entity, resp);
        return resp;
    }

    @Override
    public UserInfoEntity queryUserInfoById(Long userId) {
        UserInfoEntity entity = this.baseMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("user_id", userId));
        if (entity == null) {
            throw new UserNotFoundException(ExceptionCodeEnum.USER_NOT_FOUND_EXCEPTION);
        }
        return entity;
    }

    @Override
    public UserInfoEntity queryUserInfoByName(String username) {
        UserInfoEntity entity = this.baseMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("username", username));
        if (entity == null) {
            throw new UserNotFoundException(ExceptionCodeEnum.USER_NOT_FOUND_EXCEPTION);
        }
        return entity;
    }

    @Override
    public void alterInfo(Long toEditId, String phone, String email, Integer gender, Date birthday, String province, String city, String detailAddress, String socialName, String name) {
        this.userInfoDao.updateInfo(toEditId, phone, email, gender, birthday, province, city, detailAddress, socialName, name);
    }

    @Override
    public JsonResult<List<OrderTo>> queryOrderByUser(Long userId) {
        return orderFeignService.queryOrderByUser(userId);
    }

    @Override
    public JsonResult<List<DiscountTo>> queryDiscountByUser() {
        return discountFeignService.queryDiscount();
    }

    @Override
    public void collectHotel(Long userId, Integer hotelId) {

    }

    @Override
    public void updateTest(Long toEdit, String name) {
        this.userInfoDao.test(toEdit, name);
    }

    @Override
    public void modifyPassword(ModifyPasswordVo vo) throws BaseException {
        String phone = vo.getPhone();
        if (StringUtils.isEmpty(phone)) {
            throw new NotRegisterException(ExceptionCodeEnum.NOT_REGISTER_EXCEPTION);
        }
        UserInfoEntity entity = this.baseMapper.selectOne(new QueryWrapper<UserInfoEntity>().eq("phone", vo.getPhone()));
        if (entity == null) {
            throw new NotRegisterException(ExceptionCodeEnum.NOT_REGISTER_EXCEPTION);
        }
        if (entity.getPassword() == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encode = passwordEncoder.encode(vo.getPassword());
            entity.setPassword(encode);
            this.baseMapper.updateById(entity);
        } else {
            String oldPwd = entity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String newPwd = passwordEncoder.encode(vo.getPassword());
            if (Objects.equals(oldPwd, newPwd)) {
                throw new SamePasswordException(ExceptionCodeEnum.SAME_PASSWORD_EXCEPTION);
            } else {
                entity.setPassword(newPwd);
                this.baseMapper.updateById(entity);
            }
        }
    }

    @Override
    public Long getUserId(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new UserNotLoginException(ExceptionCodeEnum.USER_NOT_LOGIN_EXCEPTION);
        }
        Long userId = JwtHelper.getUserId(token);
        if (userId == null) {
            throw new UserNotLoginException(ExceptionCodeEnum.USER_NOT_LOGIN_EXCEPTION);
        }
        UserInfoEntity byId = this.getById(userId);
        if (byId == null) {
            throw new UserNotLoginException(ExceptionCodeEnum.USER_NOT_LOGIN_EXCEPTION);
        }
        return userId;
    }

    public PageUtils getAllUsers(Map<String, Object> params) {
        List<UserInfoEntity> allUsers = this.list();

        int curPage = 1;
        int limit = 10;
        if (params.get(Constant.PAGE) != null) {
            curPage = Integer.parseInt(params.get(Constant.PAGE).toString());
        }
        if (params.get(Constant.LIMIT) != null) {
            limit = Integer.parseInt(params.get(Constant.LIMIT).toString());
        }
        return new PageUtils(allUsers, allUsers.size(), limit, curPage);
    }

    public void deleteUserById(Long userId) {
        this.baseMapper.delete(new QueryWrapper<UserInfoEntity>().eq("user_id", userId));
    }
}