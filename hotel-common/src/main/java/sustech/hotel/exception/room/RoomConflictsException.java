package sustech.hotel.exception.room;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class RoomConflictsException extends BaseException {
    public RoomConflictsException(int state, String message) {
        super(message);
        this.state = state;
    }

    public RoomConflictsException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
