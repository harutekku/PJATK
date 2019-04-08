package GUI7_REFAKTOR;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;

public class Poly extends Figury {
    double x[]=new double[3];
    double y[]=new double[3];
    double xq,yq;//Lewy gorny rog granicy trojkata

    public Poly(){
        do{
            losujWielkosc();
        }while(poleFigury()<2000);
        xq=Math.random()*(100-width);
        yq=Math.random()*(100-height);
        x[0]=xq;
        y[1]=yq;
        do{
            x[1]=Math.random()*width+xq;
            y[0]=Math.random()*height+yq;
            x[2]=Math.random()*width+xq;
            y[2]=Math.random()*height+yq;

        }while(pole()<100);

        losujColor();
    }

    protected double pole(){
        double T=x[0]*y[1]+x[1]*y[2]+x[2]*y[0]-x[2]*y[1]-x[0]*y[2]-x[1]*y[0];
        T/=2;
        return (T>0)?T:T*-1;
    }
    @Override
    public void rysuj(Graphics2D g, int wid, int hei) {
        g.setColor(color);
        int newX[]=new int[3];
        int newY[]=new int[3];
        for(int i=0;i<3;i++){
            newX[i]=(int)(x[i]*wid/100);
            newY[i]=(int)(y[i]*hei/100);
            //System.out.println(newX[i]+" "+newY[i]);
        }

        g.fillPolygon(newX,newY,3);
    }

    @Override
    public String daneFigury() {
        return Enums.Poly+" "+x[0]+" "+y[0]+" "+x[1]+" "+y[1]+" "+x[2]+" "+y[2]+" "+color.getRGB();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {

    }
}
