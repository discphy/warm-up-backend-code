package cleancode.studycafe.tobe.exception;

import java.util.logging.Logger;

public class ProvideException extends AppException {

    private static final String PROVIDE_ERROR_MESSAGE = "주문 가능한 이용권이 없습니다.";
    private static final Logger LOGGER = Logger.getLogger(ProvideException.class.getName());

    public ProvideException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        LOGGER.info("Provide Exception: " + super.getMessage());
        return PROVIDE_ERROR_MESSAGE;
    }
}
