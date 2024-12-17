import javax.swing.*;
import java.awt.*;
import main.src.Student;
import main.src.Student_short;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CreateStudentController {
    private CreateStudent dialog;
    private boolean isCreated = false;
    private String[] studentData;
    private Student_list_DB studentListDB;

    public CreateStudentController(Frame owner) {
        dialog = new CreateStudent(owner, this);
        studentListDB = Student_list_DB.Companion.getInstance();
        setupFieldListeners();  // Добавляем слушателей для обновления кнопки "Создать"
    }

    public void showDialog() {
        dialog.setVisible(true);
    }

    public boolean isCreated() {
        return isCreated;
    }

    public String[] getStudentData() {
        return studentData;
    }

    public void adddStudent(Student_short student) {
        studentListDB.addStudent(student);
    }

    public void handleCreateButton() {
        if (validateFields()) {
            isCreated = true;
            studentData = new String[]{
                    dialog.getSurnameField().getText().trim(),
                    dialog.getNameField().getText().trim(),
                    dialog.getPatronymicField().getText().trim(),
                    dialog.getGitHubField().getText().trim(),
                    dialog.getEmailField().getText().trim(),
                    dialog.getPhoneNumberField().getText().trim(),
                    dialog.getTelegramField().getText().trim()
            };
            dialog.dispose();
        } else {
            JOptionPane.showMessageDialog(dialog,
                    "Пожалуйста, заполните все поля корректно!",
                    "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleCancelButton() {
        dialog.dispose();
    }

    private boolean validateFields() {
        // Используем методы валидации из Student для проверки каждого поля
        return Student.Examination.isValidLastName(dialog.getSurnameField().getText().trim()) &&
                Student.Examination.isValidFirstName(dialog.getNameField().getText().trim()) &&
                Student.Examination.isValidMiddleName(dialog.getPatronymicField().getText().trim()) &&
                Student.Examination.isValidGit(dialog.getGitHubField().getText().trim()) &&
                Student.Examination.isValidEmail(dialog.getEmailField().getText().trim()) &&
                Student.Examination.isValidPhone(dialog.getPhoneNumberField().getText().trim()) &&
                Student.Examination.isValidTelegram(dialog.getTelegramField().getText().trim());
    }

    // Обновляем состояние кнопки "Создать" на основе валидации
    public void updateCreateButtonState() {
        boolean allFieldsValid = validateFields();
        dialog.getCreateButton().setEnabled(allFieldsValid);
    }

    // Настройка слушателей для всех полей
    private void setupFieldListeners() {
        dialog.getSurnameField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getNameField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getPatronymicField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getPhoneNumberField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getTelegramField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getEmailField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getGitHubField().getDocument().addDocumentListener(new FieldDocumentListener(this));
    }

    // Вспомогательный слушатель, который будет проверять поля и обновлять кнопку "Создать"
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