package GUI7;


import java.awt.*;
import java.util.Random;

public abstract class Figury {
    double width,height;
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
    protected double poleFigury(){//Pole w rozumieniu prostokat jaki zajmuje
        return width*height;
    }
    abstract public void rysuj(Graphics g, int wid, int hei);
    abstract public String daneFigury();

}
