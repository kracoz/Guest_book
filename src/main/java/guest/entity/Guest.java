package guest.entity;



/**
 * Класс для хранения данных гостя
 */
public class Guest {
            // Идентификатор гостя
            private Long guestId;
            // Имя
            private String firstName;
            // Фамилия
            private String lastName;
            // Телефон
            private String phone;
            // email
            private String email;
            //номер комнаты
            private String roomNumber;

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Guest() {
    }

    public Guest(String firstName, String lastName, String phone, String email, String roomNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.roomNumber = roomNumber;
    }

    public Guest(Long guestId, String firstName, String lastName, String phone, String email, String roomNumber) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.roomNumber = roomNumber;
    }


    @Override
    public String toString() {
        return "Guest{" + "guestId=" + guestId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + ", roomNumber=" + roomNumber + '}';
    }
}