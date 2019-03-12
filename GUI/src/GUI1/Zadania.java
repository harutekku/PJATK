package GUI1;

import java.util.Scanner;

public class Zadania {
    public static void main(String[] args) {
        IsGame gra1=new IsGame() {
            @Override
            public void play() {
                System.out.println("Gra");
            }
        };


        Axe przedmiot=new Axe();
        przedmiot.scraftuj();
        System.out.println();
        System.out.println();
        przedmiot.use();



    }
}

interface IsGame{
    void play();
}

interface IsStrategic{
    void nextTurn();
}

class gra implements IsGame, IsStrategic{

    @Override
    public void play() {
        System.out.println("maslo");
    }

    @Override
    public void nextTurn() {
        System.out.println("margaryna");
    }
}

abstract class OnlineGames{
    abstract boolean login(String name, String password);
    public void play(){
        System.out.println("Podaj login i haslo:");
        String log, pas;
        Scanner sca=new Scanner(System.in);
        log=sca.next();
        pas=sca.next();
        if(login(log, pas)){
            System.out.println("Playing with friends");
        }
        else{
            System.out.println("Offline");
        }
    }
}


interface craft{
    void scraftuj();
}
abstract class obiekt implements craft{
    abstract void use();
}
class Axe extends obiekt{
    int ilosc_uzyc;
    public Axe(){
        super();
    }
    @Override
    public void scraftuj() {
        ilosc_uzyc=100;
        System.out.println("Utworzono nowy przedmiot, ma 100 uzyc");
    }

    @Override
    void use() {
        if(ilosc_uzyc<=0){
            System.out.println("Zepsute, nie dziala");
        }
        else{
            ilosc_uzyc--;
            System.out.println("Wykopano");
        }
        if(ilosc_uzyc==0){
            System.out.println("Rozdupil sie");
        }
    }
}