package ppj19;


import java.awt.*;

public class Smartphone extends Komorka {
    public Osoba[] znajomi=new Osoba[10];

    public Smartphone(String interf, Color color, int[] historia, Osoba[] znajomi){
        super(interf, color, historia);
        this.znajomi=znajomi;
    }
    public void wyswietlHistoriePolaczen(){
        for(int i=0;i<this.historia.length;i++){
            boolean byl=false;
            for(int j=0;j<this.znajomi.length;j++){
                if(this.historia[i]==this.znajomi[j].numer) {
                    byl = true;
                    System.out.println(znajomi[j].imie + " " + znajomi[j].nazwisko + " " + znajomi[j].numer);
                }
            }
            if(!byl) {
                System.out.println(this.historia[i]);
            }
        }
        System.out.println();
    }
}
