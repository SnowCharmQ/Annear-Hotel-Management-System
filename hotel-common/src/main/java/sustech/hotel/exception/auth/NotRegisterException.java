package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class NotRegisterException extends BaseException {
    public NotRegisterException(int state, String message) {
        super(message);
        this.state = state;
    }

    public NotRegisterException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
