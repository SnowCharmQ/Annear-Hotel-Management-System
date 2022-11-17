package sustech.hotel.exception.order;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class UserNotLoginException extends BaseException {
    public UserNotLoginException(int state, String message) {
        super(message);
        this.state = state;
    }
    public UserNotLoginException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
