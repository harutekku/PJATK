package GUI4;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class okieno extends JFrame {
    int x=25;
    int y=0;
    int radius=40;
    Random rand=new Random();
    Polygon poly;
    public okieno(){
        setSize(500, 525);
        setTitle("Hello, world!");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread th1=new Thread(()->{
            boolean xx=false,yy=false;
            int time=10;
            int timeA=5;
            while(true){
                try {
                    Thread.sleep(10);
                    //if(time>100||time<5)timeA*=-1;
                    //time+=timeA;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(x+radius*2>=getWidth()){
                    xx=false;
                }
                else if(x<=0){
                    xx=true;
                }
                if(y+radius*2>=getHeight()){
                    yy=false;
                }
                else if(y<=25){
                    yy=true;
                }
                if(xx)x+=10;
                else x-=10;
                if(yy)y+=10;
                else y-=10;
                //radius=(radius*2)%100;
                repaint();

            }
        });
        th1.start();
    }
    public void paint(Graphics g) {
        //super.paint(g);
        //g.clearRect(0,0,getWidth(),getHeight());

        int rg = rand.nextInt(255)%255;
        int gb = rand.nextInt(255)%255;
        int bb = rand.nextInt(255)%255;
        Color c=new Color(rg,gb,bb);
        g.setColor(c);
        poly=new Polygon();
        for(int i=0;i<=rand.nextInt(2)+3;i++){
            poly.addPoint(rand.nextInt(getWidth()),rand.nextInt(getHeight()));
        }
        kolo(g);
    }
    public void kolo(Graphics g){
        g.fillOval(x, y, radius*2,radius*2);
        //g.fillPolygon(poly);



    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            okieno pierwsze=new okieno();
        });

    }

}
