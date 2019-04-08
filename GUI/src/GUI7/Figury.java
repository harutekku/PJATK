package GUI7;


import java.awt.*;
import java.util.Random;

public abstract class Figury {
    Color color;
    Random rand=new Random();
    //Każda figura ma swoje współrzędne w przedziale 0-100 co można przełożyć na procentowe położenie w oknie


    protected void losujColor(){
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        color=new Color(r,g,b);
    }
    abstract public void rysuj(Graphics g, int wid, int hei);
    abstract public String daneFigury();

}
