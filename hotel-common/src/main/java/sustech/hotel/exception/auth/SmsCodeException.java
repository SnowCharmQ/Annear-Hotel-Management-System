package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class SmsCodeException extends BaseException {

    public SmsCodeException(int state, String message) {
        super(message);
        this.state = state;
    }
}
