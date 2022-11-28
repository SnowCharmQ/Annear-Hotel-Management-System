package sustech.hotel.exception.order;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class GuestNumberExceedLimitException extends BaseException {

    public GuestNumberExceedLimitException(String message) {
        super(message);
    }

    public GuestNumberExceedLimitException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
