package ppj22;

public class Rakieta {
    protected String nazwa;
    protected int wagaPaliwa;

    public Rakieta(String nazwa, int wagaPaliwa){
        this.nazwa=nazwa;
        this.wagaPaliwa=wagaPaliwa;
    }

    public void zatankuj(){
        this.wagaPaliwa+=(int)Math.random()*1000;
        System.out.println("Zatankowano do: "+this.wagaPaliwa);
    }
    public void start(){
        System.out.println("wzium");
    }

    public static void main(String[] args){
        Rakieta apollo=new Rakieta("Apollo",20);
        apollo.zatankuj();
        if(apollo.wagaPaliwa<1000){
            //throw new Exception("Start anulowany");
        }
        else{
            apollo.start();
        }
    }
}
