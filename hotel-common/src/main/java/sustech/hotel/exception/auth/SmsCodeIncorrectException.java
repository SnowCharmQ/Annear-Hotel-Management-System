package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class SmsCodeIncorrectException extends BaseException {
    public SmsCodeIncorrectException(int state, String message) {
        super(message);
        this.state = state;
    }

    public SmsCodeIncorrectException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
