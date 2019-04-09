package GUI7_REFAKTOR;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;

public class Rectangle extends Figury {
    double x,y;

    public Rectangle(){
        do{
            losujWielkosc();
        }while(poleFigury()>300||poleFigury()<50);
        x=Math.random()*(100-width);
        y=Math.random()*(100-height);
        losujColor();
    }
    public Rectangle(Color c,double w, double h, double x, double y){
        this.color=c;
        this.width=w;
        this.height=h;
        this.x=x;
        this.y=y;
    }

    @Override
    public void rysuj(Graphics2D g, int wid, int hei) {
        g.setColor(color);
        int newX=(int)(x*wid/100);
        int newY=(int)(y*hei/100);
        int newWi=(int)(width*wid/100);
        int newHe=(int)(height*hei/100);
        g.fillRect(newX,newY,newWi,newHe);
    }

    @Override
    public String daneFigury() {
        return Enums.Rectangle+" "+x+" "+y+" "+width+" "+height+" "+color.getRGB();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {

    }
}
