package GUI7;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Polygon extends Figures {
    private double xq[]=new double[3];//Wspolrzedne wierzcholkow
    private double yq[]=new double[3];


    Polygon(){
        do{
            randomSize();
        }while(areaOfFigure()<2000);
        x=Main.rand.nextDouble()*(100-width);
        y=Main.rand.nextDouble()*(100-height);
        xq[0]=x;
        yq[1]=y;
        do{
            xq[1]=Math.random()*width+x;
            yq[0]=Math.random()*height+y;
            xq[2]=Math.random()*width+x;
            yq[2]=Math.random()*height+y;

        }while(areaOfTriangle()<100);
        randomColor();
    }

    Polygon(DataInputStream in) throws IOException {
        read(in);
    }

    private double areaOfTriangle(){//Oblicza pole trojkata
        double T=xq[0]*yq[1]+xq[1]*yq[2]+xq[2]*yq[0]-xq[2]*yq[1]-xq[0]*yq[2]-xq[1]*yq[0];
        T/=2;
        return (T>0)?T:T*-1;
    }
    @Override
    public void draw(Graphics2D g, int frameWidth, int frameHeight) {
        g.setColor(color);
        int newX[]=new int[3];
        int newY[]=new int[3];
        for(int i=0;i<3;i++){
            newX[i]=(int)(xq[i]*frameWidth/100);
            newY[i]=(int)(yq[i]*frameHeight/100);
        }

        /*int x=(int)(this.x*frameWidth/100);     //Zobrazowanie rysowania trojkata
        int y=(int)(this.y*frameHeight/100);
        int width=(int)(this.width*frameWidth/100);
        int height=(int)(this.height*frameHeight/100);
        g.drawRect(x,y,width,height);*/

        g.drawPolygon(newX,newY,3);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.write(FiguresEnum.Polygon.getValue());
        super.write(dos);
        for(int i=0;i<xq.length;i++){
            dos.writeDouble(xq[i]);
            dos.writeDouble(yq[i]);
        }
    }
    @Override
    public void read(DataInputStream in) throws IOException {
        color=new Color(in.readInt());
        width=in.readDouble();
        height=in.readDouble();
        x=in.readDouble();
        y=in.readDouble();
        for(int i=0;i<xq.length;i++){
            xq[i]=in.readDouble();
            yq[i]=in.readDouble();
        }
    }
}
