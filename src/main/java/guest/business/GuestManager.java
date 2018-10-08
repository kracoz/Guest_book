package guest.business;

import guest.dao.GuestDAO;
import guest.dao.GuestDAOFactory;
import guest.entity.Guest;
import guest.exception.GuestBusinessException;
import guest.exception.GuestDaoException;

import java.util.List;

public class GuestManager {
    private GuestDAO dao;

    public GuestManager() {
        dao = GuestDAOFactory.getGuestDAO();
    }

    // Добавление гостя - возвращает ID добавленного гостя
    public Long addGuest(Guest contact) throws GuestBusinessException {
        try {
            return dao.addGuest(contact);
        } catch (GuestDaoException e) {
            throw new GuestBusinessException(e);
        }
    }

    // Редактирование гостя
    public void updateGuest(Guest contact) throws GuestBusinessException {
        try {
            dao.updateGuest(contact);
        } catch (GuestDaoException e) {
            throw new GuestBusinessException(e);
        }
    }

    // Удаление гостя по его ID
    public void deleteGuest(Long contactId) throws GuestBusinessException {
        try {
            dao.deleteGuest(contactId);
        } catch (GuestDaoException e) {
            throw new GuestBusinessException(e);
        }
    }

    // Получение одного гостя
    public Guest getGuest(Long contactId) throws GuestBusinessException {
        try {
            return dao.getGuest(contactId);
        } catch (GuestDaoException e) {
            throw new GuestBusinessException(e);
        }
    }

    // Получение списка контактов
    public List<Guest> findGuests() throws GuestBusinessException {
        try {
            return dao.findGuests();
        } catch (GuestDaoException e) {
            throw new GuestBusinessException(e);
        }
    }
}
