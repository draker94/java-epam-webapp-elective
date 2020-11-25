package by.training.service;

/**
 * @author Andrey Kliuchnikov
 */

public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
