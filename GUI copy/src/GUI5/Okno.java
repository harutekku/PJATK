package GUI5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Okno extends JFrame {
    RectangleContainer tab;
    public Okno(){
        setSize(500, 500);
        setTitle("Projekt");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g){
        g.clearRect(0,0,getWidth(),getHeight());
        g.setColor(Color.BLACK);
        for(int i=0;i<tab.lista.size();i++){
            paintRec(g, tab.get(i));
        }

    }
    public void paintRec(Graphics g, Rectangle rec){
        g.setColor(rec.color);
        g.fillRect(rec.x,rec.y,rec.width,rec.height);
    }
    public void setter(RectangleContainer rec){
        tab=rec;
    }
}
