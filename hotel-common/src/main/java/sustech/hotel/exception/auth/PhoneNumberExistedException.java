package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class PhoneNumberExistedException extends BaseException {
    public PhoneNumberExistedException(int state, String message) {
        super(message);
        this.state = state;
    }
}
