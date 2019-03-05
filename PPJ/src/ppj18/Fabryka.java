package ppj18;

public class Fabryka {
    public Owoc owoce=new Owoc("wszystkie");
    public void przyjmij(Owoc owoce){
        this.owoce.waga+=owoce.waga;
        if(this.owoce.waga>10){
            Sloik[] sloiki=new Sloik[10];
            for(int i=0;i<10;i++){
                Dzem owocowy=new Dzem(1000);
                this.owoce.waga-=1;
                sloiki[i]=new Sloik(owocowy);
            }
        }
        System.out.println("W fabryce zostalo "+this.owoce.waga+" owocow.\n");
    }
}
