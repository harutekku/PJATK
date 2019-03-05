package ppj19;

import java.awt.*;

public class Main {
    public static void main(String[] args){
        int[] historia=new int[10];

        for(int i=0;i<10;i++){
            historia[i]=(int)(Math.random()*899999999+100000000);
        }

        Osoba[] znajomi=new Osoba[10];

        znajomi[0] = new Osoba("Karol", "Borkowski", historia[0]);
        znajomi[1] = new Osoba("Robert", "Borewicz", (int)(Math.random()*899999999+100000000));
        znajomi[2] = new Osoba("Kamil", "Chojecki", historia[2]);
        znajomi[3] = new Osoba("Kasia", "Michalak", (int)(Math.random()*899999999+100000000));
        znajomi[4] = new Osoba("Asia", "Kaminska", historia[4]);
        znajomi[5] = new Osoba("Basia", "Dabrowska", (int)(Math.random()*899999999+100000000));
        znajomi[6] = new Osoba("Stasia", "Brzeczyszczykiewicz", historia[6]);
        znajomi[7] = new Osoba("Jasio", "Malinowski", (int)(Math.random()*899999999+100000000));
        znajomi[8] = new Osoba("Stasio", "Wojewodzki", historia[8]);
        znajomi[9] = new Osoba("Michal", "Warszawski", (int)(Math.random()*899999999+100000000));


        Telefon telefon=new Telefon("2G", Color.green);
        Komorka komorka=new Komorka("3G", Color.blue, historia);
        Smartphone s6=new Smartphone("LTE", Color.black, historia, znajomi);


        telefon.zadzwon(123456789);

        telefon.wyswietlHistoriePolaczen();

        komorka.wyswietlHistoriePolaczen();

        s6.wyswietlHistoriePolaczen();

        s6.zadzwon(123456789);

        s6.wyswietlHistoriePolaczen();
    }

}
