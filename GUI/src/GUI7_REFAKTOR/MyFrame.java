package GUI7_REFAKTOR;

import javax.swing.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        JPanel panel = new MyJPanel();
        setTitle("Projekt");
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
