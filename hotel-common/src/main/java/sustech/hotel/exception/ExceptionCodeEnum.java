package sustech.hotel.exception;

/**
 * 异常代码枚举类
 */
public enum ExceptionCodeEnum {

    UNKNOWN_EXCEPTION(10000, "Unknown Exception"),
    // 10001 - 10999 登录注册异常
    SMS_CODE_EXCEPTION(10001, "The exception occurs when sending the message"),
    SMS_CODE_HIGH_FREQUENCY_EXCEPTION(10002, "Too high frequency for SMS code"),
    USERNAME_EXISTED_EXCEPTION(10003, "The username is existed"),
    NOT_REGISTER_EXCEPTION(10004, "The phone number has not been registered"),
    PASSWORD_INCORRECT_EXCEPTION(10005, "The password is incorrect"),
    SMS_CODE_INCORRECT_EXCEPTION(10006, "The SMS code is incorrect"),
    NOT_FOUND_EXCEPTION(10007, "The user does not exist"),

    // 11001 - 11999 订单异常

    ROOM_NOT_AVAILABLE_EXCEPTION(11001, "The room is not available in that time");


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
