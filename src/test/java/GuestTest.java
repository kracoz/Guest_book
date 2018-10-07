import guest.business.GuestManager;
import guest.entity.Guest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuestTest {
    private static Logger logger = Logger.getAnonymousLogger();
    public static void main(String[] args) {
        GuestManager cm = new GuestManager();

        Guest c1 = new Guest("Андрей", "Соколов", "+7-911-890-7766", "sokolov@yandex.ru","101");
        Guest c2 = new Guest("Сергей", "Иванов", "+7-911-890-7755", "ivanov@google.com","102");
        Guest c3 = new Guest("Татьяна", "Семенова", "+7-911-890-1164", "semenova@mail.ru","103");

        logger.log(Level.INFO,"ADD GUEST ==============");
        Long cId1 = cm.addGuest(c1);
        Long cId2 = cm.addGuest(c2);
        Long cId3 = cm.addGuest(c3);
        List<Guest> result1 = cm.findGuests();
        for(Guest c : result1) {
            logger.log(Level.INFO, c.toString());
        }

        logger.log(Level.INFO,"UPDATE GUEST ==============");
        Guest change = new Guest(cId1, "Алексей", "Соколов", "+7-911-890-7766", "sokolov@yandex.ru","33");
        cm.updateGuest(change);
        List<Guest> result2 = cm.findGuests();
        for(Guest c : result2) {
            logger.log(Level.INFO, c.toString());
        }

        logger.log(Level.INFO,"DELETE GUEST ==============");
        cm.deleteGuest(cId1);
        List<Guest> result3 = cm.findGuests();
        for(Guest c : result3) {
            logger.log(Level.INFO, c.toString());
        }

        logger.log(Level.INFO,"GET GUEST ==============");

        Guest guest = cm.getGuest(cId2);
        logger.log(Level.INFO, guest.toString());
    }
}
