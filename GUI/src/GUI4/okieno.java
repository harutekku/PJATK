package GUI4;

import javax.swing.*;
import java.awt.*;

public class okieno extends JFrame {
    int x=40;
    int y=40;
    int radius=40;
    public okieno(){
        setSize(1024, 768);
        setTitle("Hello, world!");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread th1=new Thread(()->{
            while(true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                x++;
                y++;
                repaint();
            }
        });
        th1.start();
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(255, 1, 100));
        kolo(g);
    }
    public void kolo(Graphics g){
        g.drawOval(x, y, radius*2,radius*2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            okieno pierwsze=new okieno();
        });

    }

}
