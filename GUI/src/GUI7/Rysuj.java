package GUI7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rysuj extends JFrame {
    static Random ra=new Random();
    static List<Figury> figury=new ArrayList<>();
    public Rysuj(){
        setSize(500, 500);
        setTitle("Projekt");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public synchronized void paint(Graphics g){
        g.clearRect(0,0,getWidth(),getHeight());
        g.setColor(Color.BLACK);

        for(int i=0;i<figury.size();i++){
            figury.get(i).rysuj(g, getWidth(),getHeight());
        }
        //Figures test=new Polygon();
        //System.out.println(getWidth()+ " "+getHeight());
        //System.out.println();

    }
    private static void generujFigury(int n){
        for(int i=0;i<n;i++){
            int random=ra.nextInt(3);
            if(random==0){
                figury.add(new Owal());
            }
            else if(random==1){
                //figury.add(new Polygon());
            }
            else{
                //figury.add(new Rectangle());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Rysuj okno=new Rysuj();
            okno.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    //super.componentResized(e);
                    //okno.repaint();
                    //System.out.println("Repaint");

                }
            });
        });
        Thread th1=new Thread(()->{
            while(figury.size()<50){
                generujFigury(10);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        th1.start();
        //generujFigury(10);



    }
}
