package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class SmsCodeHighFrequencyException extends BaseException {

    public SmsCodeHighFrequencyException(int state, String message) {
        super(message);
        this.state = state;
    }

    public SmsCodeHighFrequencyException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
