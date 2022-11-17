package sustech.hotel.exception.order;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class RoomNotAvailableException extends BaseException {
    public RoomNotAvailableException(int state, String message) {
        super(message);
        this.state = state;
    }

    public RoomNotAvailableException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
