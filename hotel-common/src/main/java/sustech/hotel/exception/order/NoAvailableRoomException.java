package sustech.hotel.exception.order;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class NoAvailableRoomException extends BaseException {

    public NoAvailableRoomException(String message) {
        super(message);
    }

    public NoAvailableRoomException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
