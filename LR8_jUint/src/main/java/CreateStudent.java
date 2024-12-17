import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.src.Student_short;

public class CreateStudent extends JDialog {
    private JTextField surnameField, nameField, patronymicField, phoneNumberField, telegramField, emailField, gitHubField;
    private JButton createButton;

    public CreateStudent(Frame owner, CreateStudentController controller) {
        super(owner, "Создать студента", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Инициализация текстовых полей
        surnameField = new JTextField(20);
        nameField = new JTextField(20);
        patronymicField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        telegramField = new JTextField(20);
        emailField = new JTextField(20);
        gitHubField = new JTextField(20);

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
        createButton = new JButton("Создать");
        JButton cancelButton = new JButton("Отмена");

        createButton.setEnabled(false); // Кнопка не активна до валидации

        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Добавляем слушателей для валидации
        setupFieldListeners(controller);

        // Действия кнопок
        createButton.addActionListener(e -> controller.handleCreateButton());
        cancelButton.addActionListener(e -> controller.handleCancelButton());

        pack();
        setLocationRelativeTo(owner);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student_short student = new Student_short(
                        -1, // ID будет автоматически сгенерировано в БД
                        surnameField.getText(),
                        nameField.getText(),
                        patronymicField.getText(),
                        phoneNumberField.getText(),
                        telegramField.getText(),
                        emailField.getText(),
                        gitHubField.getText()
                );

                // Используем контроллер для добавления студента
                controller.adddStudent(student);
                setVisible(false);
            }
        });
    }


    // Добавляем метки и поля ввода
    private void addField(GridBagConstraints gbc, int row, String labelText, JTextField field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        add(field, gbc);
    }

    // Настроить слушателей
    private void setupFieldListeners(CreateStudentController controller) {
        surnameField.getDocument().addDocumentListener(new FieldDocumentListener(controller));
        nameField.getDocument().addDocumentListener(new FieldDocumentListener(controller));
        patronymicField.getDocument().addDocumentListener(new FieldDocumentListener(controller));
        phoneNumberField.getDocument().addDocumentListener(new FieldDocumentListener(controller));
        telegramField.getDocument().addDocumentListener(new FieldDocumentListener(controller));
        emailField.getDocument().addDocumentListener(new FieldDocumentListener(controller));
        gitHubField.getDocument().addDocumentListener(new FieldDocumentListener(controller));
    }

    // Методы для получения текста и документа
    public JTextField getSurnameField() {
        return surnameField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPatronymicField() {
        return patronymicField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public JTextField getTelegramField() {
        return telegramField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getGitHubField() {
        return gitHubField;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    // Вспомогательный слушатель
    private class FieldDocumentListener implements DocumentListener {
        private CreateStudentController controller;

        public FieldDocumentListener(CreateStudentController controller) {
            this.controller = controller;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            controller.updateCreateButtonState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            controller.updateCreateButtonState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            controller.updateCreateButtonState();
        }
    }
}