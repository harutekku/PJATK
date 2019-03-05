package ppj18;

public class Gr11_PPJ18_s18688 {
    public static void main(String[] args){
        Owoc jablko=new Owoc("Antonowka");
        jablko.wypisz();

        Dzem wisniowy=new Dzem("wisniowy");
        Dzem nieznany=new Dzem(150);
        Dzem morelowy=new Dzem("morelowy",125);

        wisniowy.wypisz();
        nieznany.wypisz();
        morelowy.wypisz();

        Sloik sloik1=new Sloik(wisniowy);
        Sloik sloik2=new Sloik(nieznany);
        Sloik sloik3=new Sloik(morelowy);

        Fabryka fabryka1=new Fabryka();

        for(int i=0;i<20;i++){
            fabryka1.przyjmij(jablko);
        }
    }
}
