package ppj19;

import java.awt.*;

public class Telefon {
    public String interfejsKomunikacyjny;
    public Color color;

    public Telefon(String interf, Color color){
        this.interfejsKomunikacyjny=interf;
        this.color=color;
    }
    public void zadzwon(int numer){
        System.out.println(numer+"\n");
    }
    public void wyswietlHistoriePolaczen(){
        System.out.println("brak historii\n");
    }
}
