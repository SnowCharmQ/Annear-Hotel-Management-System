package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class RoomNotAvailableException extends BaseException {
    public RoomNotAvailableException(int state, String message) {
        super(message);
        this.state = state;
    }
}
