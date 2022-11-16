package sustech.hotel.exception.order;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class RoomNotFoundException extends BaseException {
    public RoomNotFoundException(int state, String message) {
        super(message);
        this.state = state;
    }

    public RoomNotFoundException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
