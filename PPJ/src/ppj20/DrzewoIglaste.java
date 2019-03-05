package ppj20;

public class DrzewoIglaste extends Drzewo {
    public int iloscIgiel;
    public double dlugoscSzyszki;

    public DrzewoIglaste(boolean wiecznieZielone, int wysokosc, String przekrojDrzewa, int iloscIgiel, double dlugoscSzyszki){
        super(wiecznieZielone, wysokosc, przekrojDrzewa);
        this.iloscIgiel=iloscIgiel;
        this.dlugoscSzyszki=dlugoscSzyszki;
    }

    public String toString(){
        String a=super.toString();
        String b="Ilosc igiel: "+String.valueOf(this.iloscIgiel)+"\n";
        String c="Dlugosc szyszki: "+String.valueOf(this.dlugoscSzyszki)+"\n";
        return a+b+c;
    }
}
