package wong.andrew.webandprocessbuilder.exception;

/**
 * Created by andrewwong on 7/3/17.
 */
public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
