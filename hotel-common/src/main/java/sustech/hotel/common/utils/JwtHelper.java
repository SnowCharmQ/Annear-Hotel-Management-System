package sustech.hotel.common.utils;

import io.jsonwebtoken.*;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * jwt工具类
 * <p>
 * JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源。比如用在用户登录上；
 * JWT最重要的作用就是对 token信息的防伪作用。
 * JWT的原理：一个JWT由三个部分组成：公共部分、私有部分、签名部分。最后由这三者组合进行base64编码得到JWT。
 */
public class JwtHelper {

    // 过期时间
    private static final long tokenExpiration = 30 * 60 * 1000;
    // 签名密钥
    private static final String tokenSignKey = "OOAD";

    /**
     * 根据参数生成token
     *
     * @param userId   用户ID
     * @param username 用户名
     * @return token
     */
    public static String createToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject("HOTEL-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 根据token字符串得到用户id
     *
     * @param token token
     * @return 用户ID
     */
    public static Long getUserId(String token) {
        if (StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get("userId");
        return userId.longValue();
    }

    /**
     * 根据token字符串得到用户名称
     *
     * @param token token
     * @return 用户名
     */
    public static String getUsername(String token) {
        if (StringUtils.isEmpty(token)) return "";
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("username");
    }

}
