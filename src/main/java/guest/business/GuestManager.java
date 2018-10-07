package guest.business;

import guest.dao.GuestDAO;
import guest.dao.GuestDAOFactory;
import guest.entity.Guest;

import java.util.List;

public class GuestManager {
    private GuestDAO dao;

    public GuestManager() {
        dao = GuestDAOFactory.getGuestDAO();
    }

    // Добавление гостя - возвращает ID добавленного гостя
    public Long addGuest(Guest contact) {
        return dao.addGuest(contact);
    }

    // Редактирование гостя
    public void updateGuest(Guest contact) {
        dao.updateGuest(contact);
    }

    // Удаление гостя по его ID
    public void deleteGuest(Long contactId) {
        dao.deleteGuest(contactId);
    }

    // Получение одного гостя
    public Guest getGuest(Long contactId) {
        return dao.getGuest(contactId);
    }

    // Получение списка контактов
    public List<Guest> findGuests() {
        return dao.findGuests();
    }
}
