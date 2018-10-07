package guest.dao;

import guest.entity.Guest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuestSimpleDAO implements GuestDAO {

    private final List<Guest> guests = new ArrayList<>();

    @Override
    public Long addGuest(Guest guest) {
        Long id = generateGuestId();
        guest.setGuestId(id);
        guests.add(guest);
        return id;
    }

    @Override
    public void updateGuest(Guest guest) {
        Guest oldContact = getGuest(guest.getGuestId());
        if (oldContact != null) {
            oldContact.setFirstName(guest.getFirstName());
            oldContact.setLastName(guest.getLastName());
            oldContact.setPhone(guest.getPhone());
            oldContact.setEmail(guest.getEmail());
        }
    }

    @Override
    public void deleteGuest(Long guestId) {
        for (Iterator<Guest> it = guests.iterator(); it.hasNext(); ) {
            Guest cnt = it.next();
            if (cnt.getGuestId().equals(guestId)) {
                it.remove();
            }
        }
    }

    @Override
    public Guest getGuest(Long guestId) {
        for (Guest guest : guests) {
            if (guest.getGuestId().equals(guestId)) {
                return guest;
            }
        }
        return null;
    }

    @Override
    public List<Guest> findGuests() {
        return guests;
    }

    private Long generateGuestId() {
        Long guestId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while (getGuest(guestId) != null) {
            guestId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return guestId;
    }
}
