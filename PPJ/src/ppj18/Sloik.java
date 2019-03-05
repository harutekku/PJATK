package ppj18;

public class Sloik {
    public Dzem dzem;
    public boolean czyOtwarty;

    public Sloik(Dzem dzem){
        this.otworz();
        this.dzem=dzem;
        System.out.println("Napelniam sloik");
        this.zamknij();
        System.out.println();
    }
    public void otworz(){
        this.czyOtwarty=true;
        System.out.println("Otwieram sloik");
    }
    public void zamknij(){
        this.czyOtwarty=false;
        System.out.println("Zamykam sloik");
    }
}
