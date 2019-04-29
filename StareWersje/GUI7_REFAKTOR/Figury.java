package GUI7_REFAKTOR;


import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

public abstract class Figury implements Writable{
    double width,height,x,y;
    Color color;
    Random rand=new Random();
    //Każda figura ma swoje współrzędne w przedziale 0-100 co można przełożyć na procentowe położenie w oknie

    protected void losujColor(){
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        color=new Color(r,g,b);
    }
    protected void losujWielkosc(){//Kazda figura ma z gory nalozone ograniczenie na maksymalna wielkosc 50x50 wzgledem procent okna
        do{
            width = Math.random() * 50;
            height = Math.random() * 50;
        }while(width/height<1.0/3||height/width<1.0/3); //Ograniczenie proporcji kazdej figury
    }
    public static Figury generujFigure(){//Zwraca pseudolosowa figure
        Random ra=new Random();
        int random=ra.nextInt(3);
        if(random==0){
            return(new Owal());
        }
        else if(random==1){
            return(new Poly());
        }
        else if(random==2){
            return(new Rectangle());
        }
        return null;
    }
    public void write(DataOutputStream dos) throws IOException{
        dos.writeInt(color.getRGB());
        dos.writeDouble(width);
        dos.writeDouble(height);
        dos.writeDouble(x);
        dos.writeDouble(y);
    }
    abstract public void rysuj(Graphics2D g, int wid, int hei);
    double poleFigury(){//Pole w rozumieniu prostokat jaki zajmuje
        return width*height;
    }

}
