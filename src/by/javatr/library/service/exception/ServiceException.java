package by.javatr.library.service.exception;

import java.io.Serializable;

public class ServiceException extends Exception implements Serializable {


    private static final long serialVersionUID = 8557682029324759903L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
