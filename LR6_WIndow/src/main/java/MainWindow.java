import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    public void createWindow() {
        JFrame frame = new JFrame("Students");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Первая вкладка
        JPanel firstTab = new JPanel();
        firstTab.add(new JLabel("Вкладка 1"));
        tabbedPane.addTab("Student_list_view", firstTab);

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
}