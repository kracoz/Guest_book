package guest.gui;


import guest.business.GuestManager;
import guest.entity.Guest;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class GuestFrame extends JFrame implements ActionListener {
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";

    private final GuestManager guestManager = new GuestManager();
    private final JTable guestTable = new JTable();

    // В конструкторе мы создаем нужные элементы
    public GuestFrame() {
        // Выставляем у таблицы свойство, которое позволяет выделить
        // ТОЛЬКО одну строку в таблице
        guestTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // Используем layout manager
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 0, 5);

        // Создаем панель для кнопок
        JPanel btnPanel = new JPanel();
        // усанавливаем у него layout
        btnPanel.setLayout(gridbag);
        // Создаем кнопки
        btnPanel.add(createButton(gridbag, gbc, "Обновить", LOAD));
        btnPanel.add(createButton(gridbag, gbc, "Добавить", ADD));
        btnPanel.add(createButton(gridbag, gbc, "Исправить", EDIT));
        btnPanel.add(createButton(gridbag, gbc, "Удалить", DELETE));

        // Создаем панель для левой колокни с кнопками
        JPanel left = new JPanel();
        // Выставляем layout BorderLayout
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(btnPanel, BorderLayout.NORTH);
        // Кладем панель для левой колонки на форму слева - WEST
        add(left, BorderLayout.WEST);

        // Кладем панель со скролингом, внутри которой нахоится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(guestTable), BorderLayout.CENTER);

        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загружаем гостей
        loadGuest();
    }

    // Метод создает кнопку с заданными харктеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будетп проверяться в обработчике и мы будем знать, какую
        // именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
        button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gridbag.setConstraints(button, gbc);
        return button;
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        // Получаем команду - ActionCommand
        String action = ae.getActionCommand();
        // В зависимости от команды выполняем действия
        switch (action) {
            // Перегрузка данных
            case LOAD:
                loadGuest();
                break;
            // Добавление записи
            case ADD:
                addGuest();
                break;
            // Исправление записи
            case EDIT:
                editGuest();
                break;
            // Удаление записи
            case DELETE:
                deleteGuest();
                break;
        }
    }

    // Загрузить список гостей
    private void loadGuest() {
        // Обращаемся к классу для загрузки списка гостей
        List<Guest> guests = guestManager.findGuests();
        // Создаем модель, которой передаем полученный список
        GuestModel cm = new GuestModel(guests);
        // Передаем нашу модель таблице - и она может ее отображать
        guestTable.setModel(cm);
    }

    // Добавление гостя
    private void addGuest() {
        // Создаем диалог для ввода данных
        EditGuestDialog ecd = new EditGuestDialog();
        // Обрабатываем закрытие диалога
        saveGuest(ecd);
    }

    // Редактирование гостя
    private void editGuest() {
        // Получаем выделеннуб строку
        int sr = guestTable.getSelectedRow();
        // если строка выделена - можно ее редактировать
        if (sr != -1) {
            // Получаем ID гостя
            Long id = Long.parseLong(guestTable.getModel().getValueAt(sr, 0).toString());
            // получаем данные гостя по его ID
            Guest cnt = guestManager.getGuest(id);
            // Создаем диалог для ввода данных и передаем туда гостя
            EditGuestDialog ecd = new EditGuestDialog(cnt);
            // Обрабатываем закрытие диалога
            saveGuest(ecd);
        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для редактирования");
        }
    }

    // Удаление гостя
    private void deleteGuest() {
        // Получаем выделеннуб строку
        int sr = guestTable.getSelectedRow();
        if (sr != -1) {
            // Получаем ID гостя
            Long id = Long.parseLong(guestTable.getModel().getValueAt(sr, 0).toString());
            // Удаляем гостя
            guestManager.deleteGuest(id);
            // перегружаем список гостей
            loadGuest();
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
        }
    }

    // Общий метод для добавления и изменения гостя
    private void saveGuest(EditGuestDialog ecd) {
        // Если мы нажали кнопку SAVE
        if (ecd.isSave()) {
            // Получаем гостя из диалогового окна
            Guest cnt = ecd.getGuest();
            if (cnt.getGuestId() != null) {
                // Если ID у гостя есть, то мы его обновляем
                guestManager.updateGuest(cnt);
            } else {
                // Если у гостя нет ID - значит он новый и мы его добавляем
                guestManager.addGuest(cnt);
            }
            loadGuest();
        }
    }
}
