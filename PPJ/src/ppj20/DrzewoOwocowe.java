package ppj20;

public class DrzewoOwocowe extends DrzewoLisciaste {
    public String nazwaOwoca;

    public DrzewoOwocowe(boolean wiecznieZielone, int wysokosc, String przekrojDrzewa, int ksztaltLiscia, String nazwaOwoca){
        super(wiecznieZielone, wysokosc, przekrojDrzewa, ksztaltLiscia);
        this.nazwaOwoca=nazwaOwoca;
    }

    public String toString(){
        String a=super.toString();
        String b="Nazwa owocu: "+this.nazwaOwoca+"\n";
        return a+b;
    }
}
