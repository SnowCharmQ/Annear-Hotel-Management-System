package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class UserNotLoginException extends BaseException {
    public UserNotLoginException(int state, String message) {
        super(message);
        this.state = state;
    }
}
