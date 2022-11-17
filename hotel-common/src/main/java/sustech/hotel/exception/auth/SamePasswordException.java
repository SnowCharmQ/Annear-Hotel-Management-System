package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class SamePasswordException extends BaseException {
    public SamePasswordException(int state, String message) {
        super(message);
        this.state = state;
    }
}
