package sustech.hotel.exception.order;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class OrderClosedException extends BaseException {
    public OrderClosedException(String message) {
        super(message);
    }

    public OrderClosedException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
