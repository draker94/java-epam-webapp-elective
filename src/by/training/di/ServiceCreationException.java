package by.training.di;

public class ServiceCreationException extends Exception {
    public ServiceCreationException(String message) {
        super(message);
    }

    public ServiceCreationException(Throwable cause) {
        super(cause);
    }
}
