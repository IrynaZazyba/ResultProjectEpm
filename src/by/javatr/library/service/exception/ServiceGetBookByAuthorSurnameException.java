package by.javatr.library.service.exception;

public class ServiceGetBookByAuthorSurnameException extends ServiceException {


    public ServiceGetBookByAuthorSurnameException() {
    }

    public ServiceGetBookByAuthorSurnameException(String message) {
        super(message);
    }

    public ServiceGetBookByAuthorSurnameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceGetBookByAuthorSurnameException(Throwable cause) {
        super(cause);
    }
}
