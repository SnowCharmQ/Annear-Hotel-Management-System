package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class RoomNotFoundException extends BaseException {
    public RoomNotFoundException(int state, String message) {
        super(message);
        this.state = state;
    }
}
