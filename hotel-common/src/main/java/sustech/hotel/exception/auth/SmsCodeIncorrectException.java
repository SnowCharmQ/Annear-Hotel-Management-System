package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class SmsCodeIncorrectException extends BaseException {
    public SmsCodeIncorrectException(int state, String message) {
        super(message);
        this.state = state;
    }
}
