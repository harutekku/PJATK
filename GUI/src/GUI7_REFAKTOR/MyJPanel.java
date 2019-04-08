package GUI7_REFAKTOR;

import javax.swing.*;
import java.awt.*;

public class MyJPanel extends JPanel {
    public MyJPanel() {
        setPreferredSize(new Dimension(500, 500));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setBackground(Color.BLACK);
        g2.clearRect(0,0,getWidth(),getHeight());
        for(int i=0;i<Main.figury.size();i++){
            Main.figury.get(i).rysuj(g2, getWidth(),getHeight());
        }
    }
}
