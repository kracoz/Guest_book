package guest.gui;

import guest.entity.Guest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGuestDialog extends JDialog implements ActionListener {

    // Заголовки кнопок
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    // Размер отступа
    private static final int PAD = 10;
    // Ширина метки
    private static final int W_L = 100;
    //Ширина поля для ввода
    private static final int W_T = 300;
    // Ширина кнопки
    private static final int W_B = 120;
    // высота элемента - общая для всех
    private static final int H_B = 25;

    // Поле для ввода Имени
    private final JTextPane txtFirstName = new JTextPane();
    // Поле для ввода Фамилии
    private final JTextPane txtLastName = new JTextPane();
    // Поле для ввода Телефона
    private final JTextPane txtPhone = new JTextPane();
    // Поле для ввода E-mail
    private final JTextPane txtEmail = new JTextPane();
    // Поле для ввода номера комнаты
    private final JTextPane roomNumber = new JTextPane();

    // Поле для хранения ID гостя, если мы собираемся редактировать
    // Если это новый гость - guestId == null
    private Long guestId = null;
    // Надо ли записывать изменения после закрытия диалога
    private boolean save = false;

    public EditGuestDialog() {
        this(null);
    }

    public EditGuestDialog(Guest guest) {
        // Убираем layout - будем использовать абсолютные координаты
        setLayout(null);

        // Выстраиваем метки и поля для ввода
        buildFields();
        // Если нам передали гостя - заполняем поля формы
        initFields(guest);
        // выстариваем кнопки
        buildButtons();

        // Диалог в модальном режиме - только он активен
        setModal(true);
        // Запрещаем изменение размеров
        setResizable(false);
        // Выставляем размеры формы
        setBounds(300, 300, 450, 200);
        // Делаем форму видимой
        setVisible(true);
    }

    // Размещаем метки и поля ввода на форме
    private void buildFields() {
        // Набор метки и поля для Имени
        JLabel lblFirstName = new JLabel(GuiResource.getLabel("dialog", "givenname"));
        // Выравнивание текста с правой стороны
        lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
        // Выставляем координаты метки
        lblFirstName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        // Кладем метку на форму
        add(lblFirstName);
        // Выставляем координаты поля
        txtFirstName.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        // Делаем "бордюр" для поля
        txtFirstName.setBorder(BorderFactory.createEtchedBorder());
        // Кладем поле на форму
        add(txtFirstName);

        // Набор метки и поля для Фамилии
        JLabel lblLastName = new JLabel(GuiResource.getLabel("dialog", "surname"));
        lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLastName.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblLastName);
        txtLastName.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtLastName.setBorder(BorderFactory.createEtchedBorder());
        add(txtLastName);

        // Набор метки и поля для Телефона
        JLabel lblPhone = new JLabel(GuiResource.getLabel("dialog", "phone"));
        lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPhone.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblPhone);
        txtPhone.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtPhone.setBorder(BorderFactory.createEtchedBorder());
        add(txtPhone);

        // Набор метки и поля для Email
        JLabel lblEmail = new JLabel(GuiResource.getLabel("dialog", "email"));
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblEmail);
        txtEmail.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtEmail.setBorder(BorderFactory.createEtchedBorder());
        add(txtEmail);

        // Набор метки и поля для roomNumber
        JLabel lblroomNumber = new JLabel(GuiResource.getLabel("dialog", "room"));
        lblroomNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        lblroomNumber.setBounds(new Rectangle(PAD, 4 * H_B + PAD, W_L, H_B));
        add(lblroomNumber);
        roomNumber.setBounds(new Rectangle(W_L + 2 * PAD, 4 * H_B + PAD, W_T, H_B));
        roomNumber.setBorder(BorderFactory.createEtchedBorder());
        add(roomNumber);
    }

    // Если нам епередали гостя - заполняем поля из гостя
    private void initFields(Guest guest) {
        if (guest != null) {
            guestId = guest.getGuestId();
            txtFirstName.setText(guest.getFirstName());
            txtLastName.setText(guest.getLastName());
            txtEmail.setText(guest.getEmail());
            txtPhone.setText(guest.getPhone());
            roomNumber.setText(String.valueOf(guest.getRoomNumber()));
        }
    }

    // Размещаем кнопки на форме
    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // Если нажали кнопку SAVE (сохранить изменения) - запоминаем этой
        save = SAVE.equals(action);
        // Закрываем форму
        setVisible(false);
    }

    // Надо ли сохранять изменения
    public boolean isSave() {
        return save;
    }

    // Создаем гостя из заполенных полей, который можно будет записать
    public Guest getGuest() {
        Guest guest = new Guest(guestId, txtFirstName.getText(),
                txtLastName.getText(), txtPhone.getText(), txtEmail.getText(), Long.parseLong(roomNumber.getText()));
        return guest;
    }
}
