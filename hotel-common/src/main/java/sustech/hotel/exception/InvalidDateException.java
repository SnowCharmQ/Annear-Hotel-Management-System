package sustech.hotel.exception;

public class InvalidDateException extends BaseException {
    public InvalidDateException(int state, String message) {
        super(message);
        this.state = state;
    }
}
