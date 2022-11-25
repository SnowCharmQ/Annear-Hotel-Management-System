package sustech.hotel.exception.order;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class HotelNotFoundException extends BaseException {
    public HotelNotFoundException(int state, String message) {
        super(message);
        this.state = state;
    }

    public HotelNotFoundException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}