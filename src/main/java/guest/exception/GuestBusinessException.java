package guest.exception;

/**
 * Created by user on 08.10.2018.
 */
public class GuestBusinessException extends Exception {
    public GuestBusinessException() {
    }

    public GuestBusinessException(String message) {
        super(message);
    }

    public GuestBusinessException(Throwable cause) {
        super(cause);
    }

    public GuestBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
