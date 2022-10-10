package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class UsernameExistedException extends BaseException {
    public UsernameExistedException(int state, String message) {
        super(message);
        this.state = state;
    }
}
