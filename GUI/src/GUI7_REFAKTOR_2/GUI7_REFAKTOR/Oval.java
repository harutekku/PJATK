package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Oval extends Figures {
    public Oval(){
        do{
            randomSize();
        }while(areaOfFigure()>300|| areaOfFigure()<50);
        x=Main.rand.nextDouble()*(100-width);
        y=Main.rand.nextDouble()*(100-height);
        randomColor();
    }
    public Oval(Color c, double width, double height, double x, double y){
        this.color=c;
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
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

    public static Figures read(DataInputStream in) throws IOException {
        return(new Oval(new Color(in.readInt()),
                in.readDouble(),
                in.readDouble(),
                in.readDouble(),
                in.readDouble())
        );
    }
}
