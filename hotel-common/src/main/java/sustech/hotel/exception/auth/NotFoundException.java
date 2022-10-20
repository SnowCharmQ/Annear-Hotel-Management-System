package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(int state, String message) {
        super(message);
        this.state = state;
    }
}
