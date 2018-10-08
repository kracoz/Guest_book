package guest.gui;

import guest.entity.Guest;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GuestModel extends AbstractTableModel {
    // Список загловков для колонок в таблице
    private static final String[] headers = {
            GuiResource.getLabel("model", "id"),
            GuiResource.getLabel("model", "givenname"),
            GuiResource.getLabel("model", "surname"),
            GuiResource.getLabel("model", "email"),
            GuiResource.getLabel("model", "phone"),
            GuiResource.getLabel("model", "room")
    };

    public String[] getHeaders() {
        return headers;
    }

    // список гостей, отображаемых в таблице
    private final List<Guest> guests;

    public List<Guest> getGuest() {
        return guests;
    }

    public GuestModel(List<Guest> guests) {
        this.guests = guests;
    }

    @Override
    // Получить количество строк в таблице, размер коллекции
    public int getRowCount() {
        return guests.size();
    }

    @Override
    // Получить количество столбцов - т.е. полей
    public int getColumnCount() {
        return getHeaders().length;
    }

    @Override
    // Вернуть загловок колонки - мы его берем из массива headers
    public String getColumnName(int col) {
        return getHeaders()[col];
    }

    @Override
    // Получить объект для тображения в кокнретной ячейке таблицы
    public Object getValueAt(int row, int col) {
        Guest guest = getGuest().get(row);

        switch (col) {
            case 0:
                return guest.getGuestId().toString();
            case 1:
                return guest.getFirstName();
            case 2:
                return guest.getLastName();
            case 3:
                return guest.getEmail();
            case 4:
                return guest.getPhone();
            default:
                return guest.getRoomNumber();
        }
    }
}
