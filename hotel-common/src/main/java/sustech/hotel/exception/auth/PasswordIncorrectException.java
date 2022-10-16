package sustech.hotel.exception.auth;

import sustech.hotel.exception.BaseException;

public class PasswordIncorrectException extends BaseException {
    public PasswordIncorrectException(int state, String message) {
        super(message);
        this.state = state;
    }
}
