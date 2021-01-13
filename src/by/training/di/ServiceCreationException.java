package by.training.di;

/**
 * @author Andrey Kliuchnikov
 */

public class ServiceCreationException extends Exception {
    public ServiceCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceCreationException(String message) {
        super(message);
    }

    public ServiceCreationException(Throwable cause) {
        super(cause);
    }
}
