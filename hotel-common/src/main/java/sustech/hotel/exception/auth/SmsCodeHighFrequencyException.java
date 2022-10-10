package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class SmsCodeHighFrequencyException extends BaseException {

    public SmsCodeHighFrequencyException(int state, String message) {
        super(message);
        this.state = state;
    }
}
