import main.src.Student_short;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import main.src.Student;

public class UpdateStudentController {
    private UpdateStudent dialog;
    private boolean isUpdated = false;
    private Student_short currentStudent;
    private Student_list_DB studentListDB;

    public UpdateStudentController(Frame owner, Student_short student) {
        this.currentStudent = student;
        dialog = new UpdateStudent(owner, this, student);
        studentListDB = Student_list_DB.Companion.getInstance();
        setupFieldListeners();  // Добавляем слушателей для обновления кнопки "Обновить"
    }

    public void showDialog() {
        dialog.setVisible(true);
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void handleUpdateButton() {
        if (validateFields()) {
            isUpdated = true;

            // Обновляем текущего студента новыми данными
            currentStudent.setLastName(dialog.getSurnameField().getText().trim());
            currentStudent.setFirstName(dialog.getNameField().getText().trim());
            currentStudent.setMiddleName(dialog.getPatronymicField().getText().trim());
            currentStudent.setPhone(dialog.getPhoneNumberField().getText().trim());
            currentStudent.setTelegram(dialog.getTelegramField().getText().trim());
            currentStudent.setEmail(dialog.getEmailField().getText().trim());
            currentStudent.setGit(dialog.getGitHubField().getText().trim());

            // Обновляем студента в базе данных
            studentListDB.updateStudent(currentStudent.getId(),currentStudent);

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
        return Student.Examination.isValidLastName(dialog.getSurnameField().getText().trim()) &&
                Student.Examination.isValidFirstName(dialog.getNameField().getText().trim()) &&
                Student.Examination.isValidMiddleName(dialog.getPatronymicField().getText().trim()) &&
                Student.Examination.isValidGit(dialog.getGitHubField().getText().trim()) &&
                Student.Examination.isValidEmail(dialog.getEmailField().getText().trim()) &&
                Student.Examination.isValidPhone(dialog.getPhoneNumberField().getText().trim()) &&
                Student.Examination.isValidTelegram(dialog.getTelegramField().getText().trim());
    }

    public void updateUpdateButtonState() {
        boolean allFieldsValid = validateFields();
        dialog.getUpdateButton().setEnabled(allFieldsValid);
    }

    private void setupFieldListeners() {
        dialog.getSurnameField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getNameField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getPatronymicField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getPhoneNumberField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getTelegramField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getEmailField().getDocument().addDocumentListener(new FieldDocumentListener(this));
        dialog.getGitHubField().getDocument().addDocumentListener(new FieldDocumentListener(this));
    }

    private class FieldDocumentListener implements DocumentListener {
        private UpdateStudentController controller;

        public FieldDocumentListener(UpdateStudentController controller) {
            this.controller = controller;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            controller.updateUpdateButtonState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            controller.updateUpdateButtonState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            controller.updateUpdateButtonState();
        }
    }
}