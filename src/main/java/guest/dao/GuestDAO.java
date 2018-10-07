package guest.dao;


import guest.entity.Guest;

import java.util.List;


public interface GuestDAO {

    /**
     * Добавление гостя @guest  - возвращает ID добавленного гостя
     */
    Long addGuest(Guest guest);

    /**
     * Редактирование гостя
     *
     * @guest
     */
    void updateGuest(Guest guest);

    /**
     * Удаление гостя по его ID
     *
     * @guestId
     */
    void deleteGuest(Long guestId);

    /**
     * Получение гостя по его ID
     *
     * @guestId
     */
    Guest getGuest(Long guestId);

    /**
     * Получение списка гостей
     */
    List<Guest> findGuests();
}
