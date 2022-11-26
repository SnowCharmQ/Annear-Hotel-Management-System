package sustech.hotel.exception.others;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class InvalidDateException extends BaseException {
    public InvalidDateException(int state, String message) {
        super(message);
        this.state = state;
    }

    public InvalidDateException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
