import main.src.Student_short;
import main.src.SuperStudent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class MainWindow {
    private static final int PAGE_SIZE = 20;
    private int currentPage = 1;
    private int totalRecords = 0;

    private JTable studentTable;
    private DefaultTableModel tableModel;
    private final MainWindowController controller = new MainWindowController();
    private Student_short selectedStudent;
    private Data_list_student_short dataList;
    private Data_list<Student_short> allStudents;

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

        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            if (selectedIndex == 0) { // Если выбрана первая вкладка
                // Обновляем данные в таблице
                List<Student_short> students = controller.getStudents(PAGE_SIZE, currentPage);
                updateTableData(students, tableModel);
            }
        });

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

        String[] columnNames = {"№", "Фамилия и инициалы", "Git", "Контакт"};

        // Создание модели таблицы
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);

        // Запрет на редактирование таблицы
        studentTable.setDefaultEditor(Object.class, null);

        // Проверка на null, если dataList не инициализировано, то заполняем пустой таблицей
        if (dataList != null) {
            List<List<Object>> tableData = dataList.get_data();

            // Пропускаем первую строку (заголовки), добавляем остальные
            for (int i = 1; i < tableData.size(); i++) {
                List<Object> row = tableData.get(i);
                tableModel.addRow(row.toArray());
            }
        } else {
            System.out.println("dataList is null, cannot load table data.");
        }

        // Добавление функциональности сортировки по "Фамилия и инициалы"
        studentTable.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int col = studentTable.columnAtPoint(evt.getPoint());
                if (col == 1) { // Колонка "Фамилия и инициалы"
                    sortTableByLastName();
                }
            }
        });
        loadTableData();

        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        List<Student_short> students = fetchStudentsFromDataSource();
        totalRecords = students.size();
        updateTableData(students, tableModel);


        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevButton = new JButton("Предыдущая");
        JButton nextButton = new JButton("Следующая");
        JLabel pageLabel = new JLabel("Страница: " + currentPage + " из " + getTotalPages());

        prevButton.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                List<Student_short> st = controller.getStudents(PAGE_SIZE, currentPage);
                updateTableData(st, tableModel);
                pageLabel.setText("Страница: " + currentPage + " из " + getTotalPages());
            }
        });
        nextButton.addActionListener(e -> {
            if (currentPage < getTotalPages()) {
                currentPage++;
                List<Student_short> st = controller.getStudents(PAGE_SIZE, currentPage);
                updateTableData(st, tableModel);
                pageLabel.setText("Страница: " + currentPage + " из " + getTotalPages());
            }
        });
        paginationPanel.add(prevButton);
        paginationPanel.add(pageLabel);
        paginationPanel.add(nextButton);

        List<Student_short> studen = controller.getStudents(PAGE_SIZE, currentPage);
        updateTableData(studen, tableModel);

        // Область управления
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Изменить");
        JButton deleteButton = new JButton("Удалить");
        JButton refreshButton = new JButton("Обновить");

        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);
        controlPanel.add(refreshButton);

//        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                int[] selectedRows = studentTable.getSelectedRows();
//                if (selectedRows.length == 1) {
//                    // Одна строка выделена
//                    editButton.setEnabled(true);
//                    deleteButton.setEnabled(true);
//                } else if (selectedRows.length > 1) {
//                    // Несколько строк выделены
//                    editButton.setEnabled(false);
//                    deleteButton.setEnabled(true);
//                } else {
//                    // Никакая строка не выделена
//                    editButton.setEnabled(false);
//                    deleteButton.setEnabled(false);
//                }
//            }
//        });

        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRows = studentTable.getSelectedRows();
                if (selectedRows.length == 1) {
                    // Если выбрана одна строка, активируем кнопку "Обновить"
                    selectedStudent = getSelectedStudentFromRow(selectedRows[0]); // Получаем выбранного студента по строке
                    editButton.setEnabled(true);
                    deleteButton.setEnabled(true);

                    // Обновление метки информации
                    //studentInfoLabel.setText("Фамилия: " + selectedStudent.getLastName() + " Имя: " + selectedStudent.getFirstName());
                } else if (selectedRows.length > 1) {
                    // Несколько строк выделены
                    editButton.setEnabled(false);
                    deleteButton.setEnabled(true);
                } else {
                    // Никакая строка не выделена
                    editButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                }
            }
        });

        addButton.addActionListener(e -> {
            CreateStudentController controller = new CreateStudentController(null);
            controller.showDialog();
            if (controller.isCreated()) {
                String[] newStudent = controller.getStudentData();
                int newId = tableModel.getRowCount() + 1; // Генерация ID для нового студента
                tableModel.addRow(new Object[]{
                        newId,
                        newStudent[0], // Фамилия
                        newStudent[1], // Имя
                        newStudent[2], // Отчество
                        newStudent[3], // Git
                        newStudent[4], // Email
                        newStudent[5], // Телефон
                        newStudent[6]  // Telegram
                });
                JOptionPane.showMessageDialog(null,
                        "Студент успешно добавлен!",
                        "Успех",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            refreshTable();
        });
        editButton.addActionListener(e -> {
            if (selectedStudent != null) {
                // Если объект выбранного студента не null, открываем модальное окно
                UpdateStudentController updateController = new UpdateStudentController(null, selectedStudent);
                updateController.showDialog();  // Открытие модального окна
            } else {
                JOptionPane.showMessageDialog(null, "Пожалуйста, выберите студента для редактирования", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
            refreshTable();
        });
        deleteButton.addActionListener(e -> {
            int[] selectedRows = studentTable.getSelectedRows();
            for (int row : selectedRows) {
                if(currentPage == 1){
                    int studentId = (int) tableModel.getValueAt(row, 0);
                    controller.deleteStudent(studentId); // Вызов метода контроллера
                }
                else{
                    int studentId = (int) tableModel.getValueAt(row, 0);
                    controller.deleteStudent(studentId+(PAGE_SIZE*(currentPage-1))); // Вызов метода контроллера
                }
            }
            refreshTable();
        });
        refreshButton.addActionListener(e -> {
            List<Student_short> af = fetchStudentsFromDataSource();
            totalRecords = af.size(); // Обновляем общее количество записей
            currentPage = 1; // Возвращаемся на первую страницу
            updateTableData(af, tableModel);
            //JOptionPane.showMessageDialog(null, "Таблица обновлена!", "Обновление", JOptionPane.INFORMATION_MESSAGE);
        });


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

    private void loadTableData() {
        // Получаем данные из контроллера
        dataList = controller.getTableData(PAGE_SIZE, currentPage);
    }

    // Метод для поиска студента по id из списка
    private Student_short getStudentById(int id) {
        allStudents = controller.loadAllStudents();
        for (Student_short student : allStudents.getData()) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private Student_short getSelectedStudentFromRow(int rowIndex) {
        // Извлекаем ID из выбранной строки
        if (currentPage == 1){
            int id = (int) studentTable.getValueAt(rowIndex, 0); // Извлекаем ID из первой колонки таблицы

            // Ищем студента по ID
            Student_short student = getStudentById(id);

            if (student != null) {
                return student; // Возвращаем полный объект студента
            } else {
                return null; // Если студент не найден
            }
        }
        else{
            int id = (int) studentTable.getValueAt(rowIndex, 0); // Извлекаем ID из первой колонки таблицы

            // Ищем студента по ID
            Student_short student = getStudentById(id+(PAGE_SIZE*(currentPage-1)));

            if (student != null) {
                return student; // Возвращаем полный объект студента
            } else {
                return null; // Если студент не найден
            }
        }
    }

    private int getSelectedButtonIndex(ButtonGroup group) {
        int index = 0;
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements(); index++) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return index;
            }
        }
        return -1;
    }

    private boolean filterByNameAndInitials(Student_short student, String nameInput) {
        String[] parts = nameInput.trim().split("\\s+");
        String lastName = parts[0];

        // Если введена только фамилия
        if (parts.length == 1) {
            return student.getLastName().equalsIgnoreCase(lastName);
        }

        if (parts.length == 2 || parts.length == 3) {
            // Убираю точки, делаею заглавными
            String initials = parts[1].replace(".", "").toUpperCase();
            // Первая буква имени
            String firstNameInitial = initials.substring(0, 1);
            // Первая буква отчества
            String middleNameInitial = (initials.length() > 1) ? initials.substring(1, 2) : "";
            boolean matchesLastName = student.getLastName().equalsIgnoreCase(lastName);
            boolean matchesFirstName = student.getFirstName().toUpperCase().startsWith(firstNameInitial);
            boolean matchesMiddleName = middleNameInitial.isEmpty() ||
                    student.getMiddleName().toUpperCase().startsWith(middleNameInitial);

            return matchesLastName && matchesFirstName && matchesMiddleName;
        }
        return false;
    }

    private List<Student_short> applyFilters(String name, String phone, String telegram, String email, String git,
                                       ButtonGroup phoneGroup, ButtonGroup telegramGroup, ButtonGroup emailGroup, ButtonGroup gitGroup) {
        List<Student_short> filteredStudents = fetchStudentsFromDataSource();
        if (!name.isEmpty()) {
            filteredStudents = filteredStudents.stream()
                    .filter(s -> filterByNameAndInitials(s, name))
                    .collect(Collectors.toList());
        }
        if (!phone.isEmpty() && getSelectedButtonIndex(phoneGroup) == 0) {
            filteredStudents = filteredStudents.stream()
                    .filter(s -> s.getPhone().contains(phone))
                    .collect(Collectors.toList());
        }
        if (!telegram.isEmpty() && getSelectedButtonIndex(telegramGroup) == 0) {
            filteredStudents = filteredStudents.stream()
                    .filter(s -> s.getTelegram().contains(telegram))
                    .collect(Collectors.toList());
        }
        if (!email.isEmpty() && getSelectedButtonIndex(emailGroup) == 0) {
            filteredStudents = filteredStudents.stream()
                    .filter(s -> s.getEmail().contains(email))
                    .collect(Collectors.toList());
        }
        if (!git.isEmpty() && getSelectedButtonIndex(gitGroup) == 0) {
            filteredStudents = filteredStudents.stream()
                    .filter(s -> s.getGit().contains(git))
                    .collect(Collectors.toList());
        }
        return filteredStudents;
    }

    private void updateTableData(List<Student_short> students, DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Очистка таблицы

        // Получаем данные через get_data() из Data_list_student_short
        Data_list_student_short dataList = new Data_list_student_short(students, students.size());

        // Получаем таблицу данных
        List<List<Object>> tableData = dataList.get_data();

        // Пропускаем первую строку (заголовки), добавляем остальные
        for (int i = 1; i < tableData.size(); i++) {
            List<Object> row = tableData.get(i); // Получаем строку данных
            tableModel.addRow(row.toArray()); // Добавляем строку в таблицу
        }
    }

    // Количество страниц
    private int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / PAGE_SIZE);
    }

    private List<Student_short> fetchStudentsFromDataSource() {
        List<Student_short> students = new ArrayList<>();
        // Kotlin метод вызова get_k_n_student_short_list
        try {
            Student_list_DB dbInstance = Student_list_DB.Companion.getInstance();
            List<Student_short> fetched = dbInstance.get_k_n_student_short_list(50, currentPage).getList();
            students.addAll(fetched);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // Метод для сортировки таблицы по Фамилии
    private void sortTableByLastName() {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        int rowCount = model.getRowCount();

        List<Student_short> sortedStudents = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            int id = (int) model.getValueAt(i, 0);
            String lastName = (String) model.getValueAt(i, 1);
            String firstName = (String) model.getValueAt(i, 2);
            String middleName = (String) model.getValueAt(i, 3);
            String git = (String) model.getValueAt(i, 4);
            String email = (String) model.getValueAt(i, 5);
            String phone = (String) model.getValueAt(i, 6);
            String telegram = (String) model.getValueAt(i, 7);

            sortedStudents.add(new Student_short(id, lastName, firstName, middleName, git, email, phone, telegram));
        }
        // Сортируем студентов по Фамилии
        sortedStudents.sort(Comparator.comparing(Student_short::getLastName));
        updateTableData(sortedStudents, tableModel);
    }

    private void refreshTable() {
        List<Student_short> students = controller.getStudents(PAGE_SIZE, currentPage);
        totalRecords = students.size(); // или из источника данных
        updateTableData(students, tableModel);
    }
}