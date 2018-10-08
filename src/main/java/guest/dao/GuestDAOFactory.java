package guest.dao;

import guest.config.GlobalConfig;

public class GuestDAOFactory {
    public static GuestDAO getGuestDAO() {
        try {
            Class dao = Class.forName(GlobalConfig.getProperty("dao.class"));
            return (GuestDAO) dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
