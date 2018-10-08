package guest.exception;

/**
 * Created by user on 08.10.2018.
 */
public class GuestDaoException extends Exception {
    public GuestDaoException() {
    }

    public GuestDaoException(String message) {
        super(message);
    }

    public GuestDaoException(Throwable cause) {
        super(cause);
    }

    public GuestDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
