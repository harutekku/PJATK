package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import javax.swing.*;
import java.awt.*;

public class ChoosingPanel extends JPanel {
    public ChoosingPanel(int width, int height, MyFrame frame) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setLayout(new GridLayout(1,2));

        JButton standard=new JButton("Tryb standardowy");
        standard.addActionListener(e -> {
            frame.enableDrawPanel(this);
            Main.worstChoice();
        });

        JButton interactive=new JButton("Tryb Interaktywny");
        interactive.addActionListener(e -> {
            frame.enableMenuPanel(this);
        });

        add(standard);
        add(interactive);
        frame.pack();
    }
}
