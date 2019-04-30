import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    static DrawPanel dp=new DrawPanel();
    static NavPanel np=new NavPanel();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                Main::new
        );
    }
    private Main(){
        add(np, BorderLayout.PAGE_START);
        add(dp,BorderLayout.PAGE_END);
        try {
            while(dp.color==null) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setResizable(false);
        pack();
        setTitle("Paint vPyre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
