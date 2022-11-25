package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class SmsCodeException extends BaseException {

    public SmsCodeException(int state, String message) {
        super(message);
        this.state = state;
    }

    public SmsCodeException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
