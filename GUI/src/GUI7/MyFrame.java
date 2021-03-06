package GUI7;

import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {
    DrawPanel drawPanel;
    MenuPanel menuPanel;
    private int width=500,
                height=500;

    MyFrame(int choose) {
        setTitle("Projekt");
        setSize(new Dimension(width, height));

        if(choose==0 || choose==1){
            drawPanel=new DrawPanel(width, height,this);
            add(drawPanel);
        }
        else{
            menuPanel=new MenuPanel(width, height,this);
            add(menuPanel);
        }
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    void enableDrawPanel(JPanel myPanel, int width, int height){
        remove(myPanel);
        pack();

        drawPanel=new DrawPanel(width, height,this);
        add(drawPanel);
        pack();

    }
}
