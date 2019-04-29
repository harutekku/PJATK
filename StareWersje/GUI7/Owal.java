package GUI7;

import java.awt.*;

public class Owal extends Figury {
    double x;
    double y;
    public Owal(){

        do{
            losujWielkosc();
        }while(poleFigury()>300||poleFigury()<50);
        x=Math.random()*(100-width);
        y=Math.random()*(100-height);
        /*do {
            x = Math.random() * 100;
            y = Math.random() * 100;
            width = Math.random() * 50;
            height = Math.random() * 50;
            while (x + width > 100) width--;
            while (y + height > 100) height--;

        }while(width*height>300||width*height<50);
        */
        losujColor();
    }

    @Override
    public void rysuj(Graphics g, int wid, int hei) {
        g.setColor(color);
        int newX=(int)(x*wid/100);
        int newY=(int)(y*hei/100);
        int newWi=(int)(width*wid/100);
        int newHe=(int)(height*hei/100);
        g.drawOval(newX,newY,newWi,newHe);
    }

    @Override
    public String daneFigury() {
        return Enums.oval+" "+x+" "+y+" "+width+" "+height+" "+color.getRGB();
    }

}
