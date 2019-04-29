package GUI7;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Oval extends Figures {
    Oval(){
        do{
            randomSize();
        }while(areaOfFigure()>300|| areaOfFigure()<50);
        x=Main.rand.nextDouble()*(100-width);
        y=Main.rand.nextDouble()*(100-height);
        randomColor();
    }

    Oval(DataInputStream in) throws IOException {
        read(in);
    }

    @Override
    public void draw(Graphics2D g, int frameWidth, int frameHeight) {
        g.setColor(color);
        int newX=(int)(x*frameWidth/100);
        int newY=(int)(y*frameHeight/100);
        int newWi=(int)(width*frameWidth/100);
        int newHe=(int)(height*frameHeight/100);
        g.drawOval(newX,newY,newWi,newHe);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.write(FiguresEnum.Oval.getValue());
        super.write(dos);
    }

    @Override
    public void read(DataInputStream in) throws IOException {
        color=new Color(in.readInt());
        width=in.readDouble();
        height=in.readDouble();
        x=in.readDouble();
        y=in.readDouble();
    }
}
