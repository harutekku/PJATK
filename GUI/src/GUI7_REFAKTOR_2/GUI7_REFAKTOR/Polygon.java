package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Polygon extends Figures {
    double xq[]=new double[3];//Wspolrzedne wierzcholkow
    double yq[]=new double[3];


    public Polygon(){
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

    public Polygon(Color c, double width, double height, double x, double y, double xq[], double yq[]){
        this.color=c;
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
        this.xq=xq;
        this.yq=yq;
    }

    private double areaOfTriangle(){//Oblicza pole trojkata
        double T=xq[0]*yq[1]+xq[1]*yq[2]+xq[2]*yq[0]-xq[2]*yq[1]-xq[0]*yq[2]-xq[1]*yq[0];
        T/=2;
        return (T>0)?T:T*-1;
    }
    @Override
    public void draw(Graphics2D g, int wid, int hei) {
        g.setColor(color);
        int newX[]=new int[3];
        int newY[]=new int[3];
        for(int i=0;i<3;i++){
            newX[i]=(int)(xq[i]*wid/100);
            newY[i]=(int)(yq[i]*hei/100);
        }
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
    public static Figures read(DataInputStream in) throws IOException {
        Color color=new Color(in.readInt());
        double width=in.readDouble(),height=in.readDouble(),x=in.readDouble(),y=in.readDouble();;
        double xq[]=new double[3];
        double yq[]=new double[3];
        for(int i=0;i<xq.length;i++){
            xq[i]=in.readDouble();
            yq[i]=in.readDouble();
        }
        return(new Polygon(color,width,height,x,y,xq,yq));
    }
}
