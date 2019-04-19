package GUI7;

import java.awt.*;

public class Rectangle extends Figury {
    double x,y;

    public Rectangle(){
        do {
            x = Math.random() * 100;
            y = Math.random() * 100;
            width = Math.random() * 50;
            height = Math.random() * 50;
            while (x + width > 100) width--;
            while (y + height > 100) height--;

        }while(width*height>300||width*height<50);
        losujColor();
    }
    @Override
    public void rysuj(Graphics g, int wid, int hei) {
        g.setColor(color);
        int newX=(int)(x*wid/100);
        int newY=(int)(y*hei/100);
        int newWi=(int)(width*wid/100);
        int newHe=(int)(height*hei/100);
        g.fillRect(newX,newY,newWi,newHe);
    }

    @Override
    public String daneFigury() {
        return Enums.rectangle+" "+x+" "+y+" "+width+" "+height+" "+color.getRGB();
    }

}
