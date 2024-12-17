import main.src.Student;
import main.src.Student_short;

import javax.swing.*;
import java.awt.*;

public class UpdateStudent extends JDialog {
    private JTextField surnameField, nameField, patronymicField, phoneNumberField, telegramField, emailField, gitHubField;
    private JButton updateButton;

    public UpdateStudent(Frame owner, UpdateStudentController controller, Student_short student) {
        super(owner, "Обновить студента", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Инициализация текстовых полей с текущими значениями студента
        surnameField = new JTextField(student.getLastName(), 20);
        nameField = new JTextField(student.getFirstName(), 20);
        patronymicField = new JTextField(student.getMiddleName(), 20);
        phoneNumberField = new JTextField(student.getPhone(), 20);
        telegramField = new JTextField(student.getTelegram(), 20);
        emailField = new JTextField(student.getEmail(), 20);
        gitHubField = new JTextField(student.getGit(), 20);

        // Добавляем метки и текстовые поля
        addField(gbc, 0, "Фамилия:", surnameField);
        addField(gbc, 1, "Имя:", nameField);
        addField(gbc, 2, "Отчество:", patronymicField);
        addField(gbc, 3, "Телефон:", phoneNumberField);
        addField(gbc, 4, "Telegram:", telegramField);
        addField(gbc, 5, "Email:", emailField);
        addField(gbc, 6, "GitHub:", gitHubField);

        // Кнопки
        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Обновить");
        JButton cancelButton = new JButton("Отмена");

        updateButton.setEnabled(false);

        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Слушатели кнопок
        updateButton.addActionListener(e -> controller.handleUpdateButton());
        cancelButton.addActionListener(e -> controller.handleCancelButton());

        pack();
        setLocationRelativeTo(owner);
    }

    private void addField(GridBagConstraints gbc, int row, String labelText, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        add(field, gbc);
    }

    public JTextField getSurnameField() { return surnameField; }
    public JTextField getNameField() { return nameField; }
    public JTextField getPatronymicField() { return patronymicField; }
    public JTextField getPhoneNumberField() { return phoneNumberField; }
    public JTextField getTelegramField() { return telegramField; }
    public JTextField getEmailField() { return emailField; }
    public JTextField getGitHubField() { return gitHubField; }
    public JButton getUpdateButton() { return updateButton; }
}