import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainWindow {
    private static final int PAGE_SIZE = 20;
    private int currentPage = 1;
    private int totalRecords = 0;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public void createWindow() {
        JFrame frame = new JFrame("Students");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Первая вкладка
        JPanel studentListTab = student_list_view();
        tabbedPane.addTab("Student_list_view", studentListTab);

        // Вторая вкладка
        JPanel secondTab = new JPanel();
        secondTab.add(new JLabel("Вкладка 2"));
        tabbedPane.addTab("Вкладка 2", secondTab);

        // Третья вкладка
        JPanel thirdTab = new JPanel();
        thirdTab.add(new JLabel("Вкладка 3"));
        tabbedPane.addTab("Вкладка 3", thirdTab);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private JPanel student_list_view() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // FIO
        gbc.gridx = 0;
        gbc.gridy = 0;
        filterPanel.add(new JLabel("Фамилия и инициалы:"), gbc);
        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        filterPanel.add(nameField, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 1;
        filterPanel.add(new JLabel("Phone:"), gbc);
        ButtonGroup phoneGroup = new ButtonGroup();
        JRadioButton phoneYes = new JRadioButton("Да");
        JRadioButton phoneNo = new JRadioButton("Нет");
        JRadioButton phoneAny = new JRadioButton("Не важно");
        phoneGroup.add(phoneYes);
        phoneGroup.add(phoneNo);
        phoneGroup.add(phoneAny);
        JPanel phoneRadioPanel = new JPanel();
        phoneRadioPanel.add(phoneYes);
        phoneRadioPanel.add(phoneNo);
        phoneRadioPanel.add(phoneAny);

        gbc.gridx = 1;
        filterPanel.add(phoneRadioPanel, gbc);
        JTextField phoneField = new JTextField(20);
        phoneField.setEnabled(false);

        gbc.gridx = 2;
        filterPanel.add(phoneField, gbc);
        phoneYes.addActionListener(e -> phoneField.setEnabled(true));
        phoneNo.addActionListener(e -> phoneField.setEnabled(false));
        phoneAny.addActionListener(e -> phoneField.setEnabled(false));

        // Telegram
        gbc.gridx = 0;
        gbc.gridy = 2;
        filterPanel.add(new JLabel("Telegram:"), gbc);
        ButtonGroup telegramGroup = new ButtonGroup();
        JRadioButton telegramYes = new JRadioButton("Да");
        JRadioButton telegramNo = new JRadioButton("Нет");
        JRadioButton telegramAny = new JRadioButton("Не важно");
        telegramGroup.add(telegramYes);
        telegramGroup.add(telegramNo);
        telegramGroup.add(telegramAny);
        JPanel telegramRadioPanel = new JPanel();
        telegramRadioPanel.add(telegramYes);
        telegramRadioPanel.add(telegramNo);
        telegramRadioPanel.add(telegramAny);

        gbc.gridx = 1;
        filterPanel.add(telegramRadioPanel, gbc);
        JTextField telegramField = new JTextField(20);
        telegramField.setEnabled(false);

        gbc.gridx = 2;
        filterPanel.add(telegramField, gbc);
        telegramYes.addActionListener(e -> telegramField.setEnabled(true));
        telegramNo.addActionListener(e -> telegramField.setEnabled(false));
        telegramAny.addActionListener(e -> telegramField.setEnabled(false));

        // Email
        gbc.gridx = 0;
        gbc.gridy = 3;
        filterPanel.add(new JLabel("Email:"), gbc);
        ButtonGroup emailGroup = new ButtonGroup();
        JRadioButton emailYes = new JRadioButton("Да");
        JRadioButton emailNo = new JRadioButton("Нет");
        JRadioButton emailAny = new JRadioButton("Не важно");
        emailGroup.add(emailYes);
        emailGroup.add(emailNo);
        emailGroup.add(emailAny);
        JPanel emailRadioPanel = new JPanel();
        emailRadioPanel.add(emailYes);
        emailRadioPanel.add(emailNo);
        emailRadioPanel.add(emailAny);

        gbc.gridx = 1;
        filterPanel.add(emailRadioPanel, gbc);
        JTextField emailField = new JTextField(20);
        emailField.setEnabled(false);

        gbc.gridx = 2;
        filterPanel.add(emailField, gbc);
        emailYes.addActionListener(e -> emailField.setEnabled(true));
        emailNo.addActionListener(e -> emailField.setEnabled(false));
        emailAny.addActionListener(e -> emailField.setEnabled(false));

        // Git
        gbc.gridx = 0;
        gbc.gridy = 4;
        filterPanel.add(new JLabel("Git:"), gbc);
        ButtonGroup gitGroup = new ButtonGroup();
        JRadioButton gitYes = new JRadioButton("Да");
        JRadioButton gitNo = new JRadioButton("Нет");
        JRadioButton gitAny = new JRadioButton("Не важно");
        gitGroup.add(gitYes);
        gitGroup.add(gitNo);
        gitGroup.add(gitAny);
        JPanel gitRadioPanel = new JPanel();
        gitRadioPanel.add(gitYes);
        gitRadioPanel.add(gitNo);
        gitRadioPanel.add(gitAny);

        gbc.gridx = 1;
        filterPanel.add(gitRadioPanel, gbc);
        JTextField gitField = new JTextField(20);
        gitField.setEnabled(false);

        gbc.gridx = 2;
        filterPanel.add(gitField, gbc);
        gitYes.addActionListener(e -> gitField.setEnabled(true));
        gitNo.addActionListener(e -> gitField.setEnabled(false));
        gitAny.addActionListener(e -> gitField.setEnabled(false));

        // Таблица
        String[] columnNames = {"ID", "Фамилия", "Имя", "Отчество", "Git", "Email", "Телефон", "Telegram"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        // Запрет на редактирование таблицы
        studentTable.setDefaultEditor(Object.class, null);
        // Сортировка по Фамилия
        studentTable.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int col = studentTable.columnAtPoint(evt.getPoint());
                // Провверка нажатия по Фамилия
                if (col == 1) {
                    sortTableByLastName();
                }
            }
        });
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        // Добавление тестовые хардкодинговых данные
        List<Student> students = getDummyStudents();
        totalRecords = students.size();
        updateTableData(students, tableModel);


        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevButton = new JButton("Предыдущая");
        JButton nextButton = new JButton("Следующая");
        JLabel pageLabel = new JLabel("Страница: " + currentPage + " из " + getTotalPages());
        prevButton.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                updateTableData(students, tableModel);
                pageLabel.setText("Страница: " + currentPage + " из " + getTotalPages());
            }
        });
        nextButton.addActionListener(e -> {
            if (currentPage < getTotalPages()) {
                currentPage++;
                updateTableData(students, tableModel);
                pageLabel.setText("Страница: " + currentPage + " из " + getTotalPages());
            }
        });
        paginationPanel.add(prevButton);
        paginationPanel.add(pageLabel);
        paginationPanel.add(nextButton);

        // Область управления
        JPanel controlPanel = new JPanel();
        JButton addButton1 = new JButton("Кнопка1");
        JButton addButton2 = new JButton("Кнопка2");
        JButton addButton3 = new JButton("Кнопка3");
        controlPanel.add(addButton1);
        controlPanel.add(addButton2);
        controlPanel.add(addButton3);

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.add(tableScrollPane);
        centralPanel.add(paginationPanel);

        // Добавляем области в панель вкладки
        panel.add(filterPanel, BorderLayout.NORTH);
        panel.add(centralPanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void updateTableData(List<Student> students, DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        int statrIndex = (currentPage - 1) * PAGE_SIZE;
        int endIndex = Math.min(currentPage * PAGE_SIZE, totalRecords);
        for (int i = statrIndex; i < endIndex; i++) {
            Student student = students.get(i);
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getLastName(),
                    student.getFirstName(),
                    student.getMiddleName(),
                    student.getGit(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getTelegram()
            });
        }
    }

    private void updateStudent(List<Student> students, DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        int endIndex = Math.min(currentPage * PAGE_SIZE, totalRecords);
        for (int i = 0; i < endIndex; i++) {
            Student student = students.get(i);
            tableModel.addRow(new Object[]{
                    student.getId(),
                    student.getLastName(),
                    student.getFirstName(),
                    student.getMiddleName(),
                    student.getGit(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getTelegram()
            });
        }
    }

    // Количество страниц
    private int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / PAGE_SIZE);
    }

    private List<Student> getDummyStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Иванов", "Иван", "Иванович", "https://github.com/ivanov", "ivanov@example.com", "123-456", "@ivanov"));
        students.add(new Student(2, "Петров", "Петр", "Петрович", "https://github.com/petrov", "petrov@example.com", "987-654", "@petrov"));
        students.add(new Student(3, "Сидорова", "Анна", "Сергеевна", "", "sid@example.com", "321-654", "@sid"));
        students.add(new Student(4, "Иванов", "Иван", "Иванович", "https://github.com/ivanov", "ivanov@example.com", "123-456", "@ivanov"));
        students.add(new Student(5, "Петров", "Петр", "Петрович", "https://github.com/petrov", "petrov@example.com", "987-654", "@petrov"));
        students.add(new Student(6, "Сидорова", "Анна", "Сергеевна", "", "sid@example.com", "321-654", "@sid"));
        students.add(new Student(7, "Иванов", "Иван", "Иванович", "https://github.com/ivanov", "ivanov@example.com", "123-456", "@ivanov"));
        students.add(new Student(8, "Петров", "Петр", "Петрович", "https://github.com/petrov", "petrov@example.com", "987-654", "@petrov"));
        students.add(new Student(9, "Сидорова", "Анна", "Сергеевна", "", "sid@example.com", "321-654", "@sid"));
        students.add(new Student(10, "Иванов", "Иван", "Иванович", "https://github.com/ivanov", "ivanov@example.com", "123-456", "@ivanov"));
        students.add(new Student(11, "Петров", "Петр", "Петрович", "https://github.com/petrov", "petrov@example.com", "987-654", "@petrov"));
        students.add(new Student(12, "Сидорова", "Анна", "Сергеевна", "", "sid@example.com", "321-654", "@sid"));
        students.add(new Student(13, "Иванов", "Иван", "Иванович", "https://github.com/ivanov", "ivanov@example.com", "123-456", "@ivanov"));
        students.add(new Student(14, "Петров", "Петр", "Петрович", "https://github.com/petrov", "petrov@example.com", "987-654", "@petrov"));
        students.add(new Student(15, "Сидорова", "Анна", "Сергеевна", "", "sid@example.com", "321-654", "@sid"));
        students.add(new Student(16, "Иванов", "Иван", "Иванович", "https://github.com/ivanov", "ivanov@example.com", "123-456", "@ivanov"));
        students.add(new Student(17, "Петров", "Петр", "Петрович", "https://github.com/petrov", "petrov@example.com", "987-654", "@petrov"));
        students.add(new Student(18, "Сидорова", "Анна", "Сергеевна", "", "sid@example.com", "321-654", "@sid"));
        students.add(new Student(19, "Иванов", "Иван", "Иванович", "https://github.com/ivanov", "ivanov@example.com", "123-456", "@ivanov"));
        students.add(new Student(20, "Петров", "Петр", "Петрович", "https://github.com/petrov", "petrov@example.com", "987-654", "@petrov"));
        students.add(new Student(21, "Сидорова", "Анна", "Сергеевна", "", "sid@example.com", "321-654", "@sid"));
        students.add(new Student(22, "Иванов", "Иван", "Иванович", "https://github.com/ivanov", "ivanov@example.com", "123-456", "@ivanov"));
        students.add(new Student(23, "Петров", "Петр", "Петрович", "https://github.com/petrov", "petrov@example.com", "987-654", "@petrov"));
        students.add(new Student(24, "Сидорова", "Анна", "Сергеевна", "", "sid@example.com", "321-654", "@sid"));
        return students;
    }

    // Метод для сортировки таблицы по Фамилии
    private void sortTableByLastName() {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        int rowCount = model.getRowCount();

        List<Student> sortedStudents = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            int id = (int) model.getValueAt(i, 0);
            String lastName = (String) model.getValueAt(i, 1);
            String firstName = (String) model.getValueAt(i, 2);
            String middleName = (String) model.getValueAt(i, 3);
            String git = (String) model.getValueAt(i, 4);
            String email = (String) model.getValueAt(i, 5);
            String phone = (String) model.getValueAt(i, 6);
            String telegram = (String) model.getValueAt(i, 7);

            sortedStudents.add(new Student(id, lastName, firstName, middleName, git, email, phone, telegram));
        }
        // Сортируем студентов по Фамилии
        sortedStudents.sort(Comparator.comparing(Student::getLastName));
        updateStudent(sortedStudents, tableModel);
    }

    static class Student {
        private final int id;
        private final String lastName;
        private final String firstName;
        private final String middleName;
        private final String git;
        private final String email;
        private final String phone;
        private final String telegram;

        public Student(int id, String lastName, String firstName, String middleName, String git, String email, String phone, String telegram) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleName = middleName;
            this.git = git;
            this.email = email;
            this.phone = phone;
            this.telegram = telegram;
        }
        public int getId() {
            return id;
        }
        public String getLastName() {
            return lastName;
        }
        public String getFirstName() {
            return firstName;
        }
        public String getMiddleName() {
            return middleName;
        }
        public String getGit() {
            return git;
        }
        public String getEmail() {
            return email;
        }
        public String getPhone() {
            return phone;
        }
        public String getTelegram() {
            return telegram;
        }
    }
}