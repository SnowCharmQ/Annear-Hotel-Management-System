package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class SamePasswordException extends BaseException {
    public SamePasswordException(int state, String message) {
        super(message);
        this.state = state;
    }

    public SamePasswordException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
