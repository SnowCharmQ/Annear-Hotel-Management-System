package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class PasswordIncorrectException extends BaseException {
    public PasswordIncorrectException(int state, String message) {
        super(message);
        this.state = state;
    }

    public PasswordIncorrectException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
