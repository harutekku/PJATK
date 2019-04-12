package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    Graphics2D g2;
    public DrawPanel(int width, int height, MyFrame frame) {
        setPreferredSize(new Dimension(width, height));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2=(Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setBackground(Color.BLACK);
        g2.clearRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < Main.figures.size(); i++) {
            Main.figures.get(i).draw(g2, getWidth(), getHeight());
        }
    }
}