package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class PhoneNumberExistedException extends BaseException {
    public PhoneNumberExistedException(int state, String message) {
        super(message);
        this.state = state;
    }

    public PhoneNumberExistedException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
