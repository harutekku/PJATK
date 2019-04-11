package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    DrawPanel drawPanel;
    MenuPanel menuPanel;
    ChoosingPanel choosingPanel;
    private int width=500,
                height=500;

    public MyFrame(int choose) {
        setTitle("Projekt");
        setMinimumSize(new Dimension(300,200));
        setSize(new Dimension(width, height));

        if(choose==0){
            drawPanel=new DrawPanel(getWidth(), getHeight(),this);
            add(drawPanel);
        }
        else{
            choosingPanel=new ChoosingPanel(getWidth(), getHeight(),this);
            add(choosingPanel);
        }
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void enableDrawPanel(JPanel myPanel){
        remove(myPanel);
        pack();

        drawPanel=new DrawPanel(getWidth(), getHeight(),this);
        add(drawPanel);
        pack();
        setMinimumSize(new Dimension(200,200));

    }
    public void enableMenuPanel(JPanel myPanel){
        remove(myPanel);
        pack();

        menuPanel=new MenuPanel(getWidth(), getHeight(),this);
        add(menuPanel);
        pack();
        setMinimumSize(new Dimension(300,200));

    }
}
