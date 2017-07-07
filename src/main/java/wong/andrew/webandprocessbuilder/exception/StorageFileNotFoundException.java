package wong.andrew.webandprocessbuilder.exception;

/**
 * Created by andrewwong on 7/3/17.
 */
public class StorageFileNotFoundException extends RuntimeException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
