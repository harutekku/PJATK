package ppj19;


import java.awt.*;

public class Komorka extends Telefon {
    public int[] historia=new int[10];

    public Komorka(String interf, Color color, int[] historia){
        super(interf, color);
        this.historia=historia;
    }
    public void zadzwon(int numer){
        System.out.println(numer+"\n");
        for(int i=8;i>=0;i--){
            this.historia[i+1]=this.historia[i];
        }
        this.historia[0]=numer;
    }
    public void wyswietlHistoriePolaczen(){
        for(int i=0;i<historia.length;i++){
            System.out.println(historia[i]);
        }
        System.out.println();
    }
}
