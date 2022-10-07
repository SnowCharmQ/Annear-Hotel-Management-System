package sustech.hotel.exception;

/**
 * 异常代码枚举类
 */
public enum ExceptionCodeEnum {

    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    // 10001 - 10999 登录注册异常
    SMS_CODE_EXCEPTION(10002, "短信验证码频率过高"),
    USERNAME_EXISTED_EXCEPTION(10003, "用户名已经存在");
    // 11001 - 11999 订单异常


    private final int code;
    private final String message;

    ExceptionCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
