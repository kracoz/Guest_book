package guest.dao;

public class GuestDAOFactory {
    public static GuestDAO getGuestDAO() {
        return new GuestSimpleDAO();
    }
}
