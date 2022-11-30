package sustech.hotel.exception;

/**
 * 异常代码枚举类
 */
public enum ExceptionCodeEnum {

    UNKNOWN_EXCEPTION(10000, "Unknown Exception"),
    // 10001 - 10999 登录注册异常
    SMS_CODE_EXCEPTION(10001, "The exception occurs when sending the message"),
    SMS_CODE_HIGH_FREQUENCY_EXCEPTION(10002, "Too high frequency for SMS code"),
    PHONE_NUMBER_EXISTED_EXCEPTION(10003, "The phone number is existed"),
    NOT_REGISTER_EXCEPTION(10004, "The phone number has not been registered"),
    PASSWORD_INCORRECT_EXCEPTION(10005, "The password is incorrect"),
    SMS_CODE_INCORRECT_EXCEPTION(10006, "The SMS code is incorrect"),
    USER_NOT_FOUND_EXCEPTION(10007, "The user does not exist"),
    INVALID_INPUT_EXCEPTION(10008, "The input is invalid"),
    SAME_PASSWORD_EXCEPTION(10009, "The new password can not be same as the old password"),

    // 11000 - 11999 订单异常

    ROOM_NOT_AVAILABLE_EXCEPTION(11001, "The room is not available in that time"),
    USER_NOT_LOGIN_EXCEPTION(11002, "The user has not login in"),
    ROOM_NOT_FOUND_EXCEPTION(11003, "The room does not exist"),

    HOTEL_NOT_FOUND_EXCEPTION(11004, "The hotel does not exist"),
    CREATE_ORDER_EXCEPTION(11005, "Create order exception."),
    GUEST_NUMBER_EXCEED_LIMIT_EXCEPTION(11006, "The number of guest number exceed the limit of the room."),

    NO_AVAILABLE_ROOM_EXCEPTION(11007, "No available room."),

    ORDER_NOT_EXIST_EXCEPTION(11008, "The order does not exist."),
    ORDER_CLOSED_EXCEPTION(11009, "The order has been closed."),

    ORDER_NOT_FINISH_EXCEPTION(11010, "Order haven't finish."),
    // 20000 ~ others

    INVALID_DATE_EXCEPTION(20000, "The date is invalid");

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
