package guest.dao;


import guest.entity.Guest;
import guest.exception.GuestDaoException;

import java.util.List;


public interface GuestDAO {

    /**
     * Добавление гостя @guest  - возвращает ID добавленного гостя
     */
    Long addGuest(Guest guest) throws GuestDaoException;

    /**
     * Редактирование гостя
     *
     * @guest
     */
    void updateGuest(Guest guest) throws GuestDaoException;

    /**
     * Удаление гостя по его ID
     *
     * @guestId
     */
    void deleteGuest(Long guestId) throws GuestDaoException;

    /**
     * Получение гостя по его ID
     *
     * @guestId
     */
    Guest getGuest(Long guestId) throws GuestDaoException;

    /**
     * Получение списка гостей
     */
    List<Guest> findGuests() throws GuestDaoException;
}
