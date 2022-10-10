package sustech.hotel.exception;

public class BaseException extends RuntimeException {

    protected int state;

    public BaseException(String message) {
        super(message);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
