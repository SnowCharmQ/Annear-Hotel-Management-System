package sustech.hotel.exception.order;

import sustech.hotel.exception.BaseException;
import sustech.hotel.exception.ExceptionCodeEnum;

public class DuplicateOrderSubmissionException extends BaseException {

    public DuplicateOrderSubmissionException(String message) {
        super(message);
    }

    public DuplicateOrderSubmissionException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
        this.state = exceptionCodeEnum.getCode();
    }
}
