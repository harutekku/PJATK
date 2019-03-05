import java.util.Map;
import java.util.TreeMap;

public class obiekt {
    private double czas;
    private double czas_rzeczywisty;
    private Map<String, Double> wymagania=new TreeMap<>();
    //private double zapotrzebowanie;
    private int ile_maszyn;

    public obiekt(double czas, int ile_tworzy, String[] wymag, double ile_potrzeba_na_sekunde, double predkosc_maszyny){
        this.czas=czas/ile_tworzy;
        this.czas_rzeczywisty=this.czas/predkosc_maszyny;
        //this.zapotrzebowanie=ile_potrzeba_na_sekunde;
        this.ile_maszyn=((int)(Math.ceil(ile_potrzeba_na_sekunde)*this.czas_rzeczywisty));
        for(int i=0;i<wymag.length;i+=2){
            this.wymagania.put(wymag[i],Double.valueOf(wymag[i+1])*((double)ile_maszyn));
        }
    }

    public obiekt(double czas, int ile_tworzy, String[] wymag, double ile_potrzeba_na_sekunde){
        this.czas=czas/ile_tworzy;
        this.czas_rzeczywisty=this.czas/0.75;
        //this.zapotrzebowanie=ile_potrzeba_na_sekunde;
        this.ile_maszyn=((int)(Math.ceil(ile_potrzeba_na_sekunde*this.czas_rzeczywisty)));

        for(int i=0;i<wymag.length;i+=2){
            this.wymagania.put(wymag[i],Double.valueOf(wymag[i+1])/(double)ile_tworzy*( (double)ile_maszyn/czas_rzeczywisty));
        }
        this.wymagania.put("maszyn "+this.getClass().getSimpleName(), Double.valueOf(ile_maszyn));
    }

    public Map<String, Double> mapaWymagan(){
        return this.wymagania;
    }

    public Map<String, Double> mapaWymagan(Map<String, Double> podstawy){
        for (Map.Entry<String, Double> entry : podstawy.entrySet()){
            if(this.wymagania.get(entry.getKey())==null){
                this.wymagania.put(entry.getKey(),entry.getValue());
            }
            else{
                this.wymagania.put(entry.getKey(),(this.wymagania.get(entry.getKey())+entry.getValue()));
            }
        }
        return this.wymagania;
    }

    public String toString(){
        String wymaganka="czas: "+this.czas_rzeczywisty+"\n";
        for(Map.Entry<String,Double> entry: this.wymagania.entrySet()){
            wymaganka+=entry.getKey()+" "+entry.getValue()+" ("+entry.getValue()*60+" na minute)\n";
        }
        //wymaganka+="maszyn: "+this.ile_maszyn;
        return wymaganka;
    }

}
