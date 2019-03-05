package ppj18;

public class Dzem {
    public String smak;
    public double waga;

    public Dzem(String smak, double waga){
        this.smak=smak;
        this.waga=waga;
    }
    public Dzem(double waga){
        this("No name", waga);
    }
    public Dzem(String smak){
        this(smak, 100.0);
    }

    public void wypisz(){
        System.out.println("Smak: "+this.smak+"\nWaga: "+this.waga+"\n");
    }
}
