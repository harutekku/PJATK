package GUI7_REFAKTOR;

import javax.swing.*;

public class MyFrame extends JFrame {
    MyJPanel panel;
    public MyFrame() {
        panel=new MyJPanel(500,500);
        setTitle("Projekt");
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
