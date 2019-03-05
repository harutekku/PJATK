package ppj20;

public class DrzewoLisciaste extends Drzewo {
    public int ksztaltLiscia;

    public DrzewoLisciaste(boolean wiecznieZielone, int wysokosc, String przekrojDrzewa, int ksztaltLiscia){
        super(wiecznieZielone, wysokosc, przekrojDrzewa);
        this.ksztaltLiscia =ksztaltLiscia;
    }

    public String toString(){
        String a=super.toString();
        String b="Ksztalt liscia: "+String.valueOf(this.ksztaltLiscia)+"\n";
        return a+b;
    }
}
