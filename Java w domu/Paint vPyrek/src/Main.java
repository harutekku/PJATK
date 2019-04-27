import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static NavPanel np=new NavPanel();
    public static DrawPanel dp=new DrawPanel();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
        ()-> {
                    new Main();
                }
        );
    }
    public Main(){
        add(np, BorderLayout.PAGE_START);
        add(dp,BorderLayout.PAGE_END);
        setResizable(false);
        pack();
        setTitle("Paint vPyre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
