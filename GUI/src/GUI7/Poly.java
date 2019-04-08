package GUI7;

import java.awt.*;

public class Poly extends Figury {
    double x[]=new double[3];
    double y[]=new double[3];

    public Poly(){
        while(pole()<200||pole()>1000){
            for(int i=0;i<x.length;i++){
                x[i]=Math.random()*100;
                y[i]=Math.random()*100;
            }
            x[2]=Math.random()*100;
            y[2]=Math.random()*100;
        }
        losujColor();
    }
    private double pole(){
        double T=x[0]*y[1]+x[1]*y[2]+x[2]*y[0]-x[2]*y[1]-x[0]*y[2]-x[1]*y[0];
        T/=2;
        return (T>0)?T:T*-1;
    }
    @Override
    public void rysuj(Graphics g, int wid, int hei) {
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
        return Enums.triangle+" "+x[0]+" "+y[0]+" "+x[1]+" "+y[1]+" "+x[2]+" "+y[2]+" "+color.getRGB();
    }

}
