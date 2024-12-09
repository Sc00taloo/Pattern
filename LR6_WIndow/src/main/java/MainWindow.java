import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
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
        String[] columnNames = {"ID", "Фамилия", "Имя", "Отчество", "Телефон", "Telegram", "Email", "Git"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable studentTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentTable);

        // Область управления
        JPanel controlPanel = new JPanel();
        JButton addButton1 = new JButton("Кнопка1");
        JButton addButton2 = new JButton("Кнопка2");
        JButton addButton3 = new JButton("Кнопка3");
        controlPanel.add(addButton1);
        controlPanel.add(addButton2);
        controlPanel.add(addButton3);

        // Добавляем области в панель вкладки
        panel.add(filterPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

}