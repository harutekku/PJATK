package GUI7_REFAKTOR_2.GUI7_REFAKTOR;


import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Figures implements Writable {
    //Każda figura ma swoje współrzędne w przedziale 0-100 co można przełożyć na procentowe położenie w oknie
    protected Color color;
    protected double width,height,x,y;

    abstract public void draw(Graphics2D g, int wid, int hei);
    static void generateFigures(int n){
        while(Main.figures.size()<n){
            Main.figures.add(Figures.generateFigure());
        }
    }

    void randomColor(){
        int r = Main.rand.nextInt(256);
        int g = Main.rand.nextInt(256);
        int b = Main.rand.nextInt(256);
        color=new Color(r,g,b);
    }
    void randomSize(){//Kazda figura ma z gory nalozone ograniczenie na maksymalna wielkosc 50x50 wzgledem procent okna
        do{
            width = Math.random() * 50;
            height = Math.random() * 50;
        }while(width/height<1.0/3||height/width<1.0/3); //Ograniczenie proporcji kazdej figures
    }
    double areaOfFigure(){//Pole w rozumieniu prostokat jaki zajmuje
        return width*height;
    }
    static Figures generateFigure(){//Zwraca pseudolosowa figure
        int random=Main.rand.nextInt(3);
        if(random==0){
            return(new Oval());
        }
        else if(random==1){
            return(new Polygon());
        }
        else if(random==2){
            return(new Rectangle());
        }
        return null;
    }
    public void write(DataOutputStream dos) throws IOException{ //Metoda zapisuje jedynie naglowek figury, kazda figura musi miec rozszerzona metode
        dos.writeInt(color.getRGB());
        dos.writeDouble(width);
        dos.writeDouble(height);
        dos.writeDouble(x);
        dos.writeDouble(y);
    }

}
