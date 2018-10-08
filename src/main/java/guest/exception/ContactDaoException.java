package guest.exception;

/**
 * Created by user on 08.10.2018.
 */
public class ContactDaoException extends Exception {
    public ContactDaoException() {
    }

    public ContactDaoException(String message) {
        super(message);
    }

    public ContactDaoException(Throwable cause) {
        super(cause);
    }

    public ContactDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
