import javax.swing.*;
import java.awt.*;

public class MainWindow {
    public void createWindow() {
        JFrame frame = new JFrame("Java Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JLabel label = new JLabel("Тест", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        frame.add(label);
        frame.setVisible(true);
    }
}