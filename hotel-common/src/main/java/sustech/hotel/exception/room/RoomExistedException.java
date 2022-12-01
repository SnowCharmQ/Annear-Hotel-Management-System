package sustech.hotel.exception.room;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class RoomExistedException extends BaseException {
    public RoomExistedException(String message) {
        super(message);
    }

    public RoomExistedException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
