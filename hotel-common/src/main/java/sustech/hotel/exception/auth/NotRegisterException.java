package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class NotRegisterException extends BaseException {
    public NotRegisterException(int state, String message) {
        super(message);
        this.state = state;
    }
}
