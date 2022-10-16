package sustech.hotel.member.dao;

import sustech.hotel.member.entity.UserInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.Date;

@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
	void updateInfo(Long toEditId, String phone, String email, String avatar, Integer gender, Date birthday, String province, String city, String detailAddress, String job, Integer isBlocked, String socialName);
}