package GUI5;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RectangleContainer {
    List<Rectangle> lista;
    Random rand=new Random();
    Okno okno;

    public RectangleContainer(Okno okno){
        lista=new ArrayList<>();
        this.okno=okno;
        okno.setter(this);
    }
    public void add(Rectangle r){
        lista.add(r);
    }
    public Rectangle get(int i){
        return lista.get(i);
    }
    public void generate(){
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        Color color=new Color(r,g,b);
        int width = rand.nextInt(okno.getWidth());
        int height = rand.nextInt(okno.getHeight());
        int x = rand.nextInt(okno.getWidth()-width);
        int y = rand.nextInt(okno.getHeight()-height);
        Rectangle rec=new Rectangle(x,y,width,height,color);
        this.add(rec);
    }
    public void thread(){
        Thread th = new Thread(() -> {
            while(true){
                generate();
                okno.repaint();
                //System.out.println("Generuje");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        th.start();
    }

    public static void main(String[] args) {
        Okno okno=new Okno();
        RectangleContainer rec=new RectangleContainer(okno);
        rec.thread();
    }

}