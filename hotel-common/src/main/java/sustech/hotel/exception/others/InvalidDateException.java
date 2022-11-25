package sustech.hotel.exception.others;

import sustech.hotel.exception.BaseException;

public class InvalidDateException extends BaseException {
    public InvalidDateException(int state, String message) {
        super(message);
        this.state = state;
    }
}
