package guest.exception;

/**
 * Created by user on 08.10.2018.
 */
public class ContactBusinessException extends Exception {
    public ContactBusinessException() {
    }

    public ContactBusinessException(String message) {
        super(message);
    }

    public ContactBusinessException(Throwable cause) {
        super(cause);
    }

    public ContactBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
