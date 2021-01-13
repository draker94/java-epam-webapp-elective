package by.training.connection;

/**
 * @author Andrey Kliuchnikov
 */

public class ConnectionPoolException extends Exception {
    ConnectionPoolException() {
        super();
    }

    ConnectionPoolException(String message) {
        super(message);
    }

    ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
