package ppj20;

public class Drzewo {
    public boolean wiecznieZielone;
    public int wysokosc;
    public String przekrojDrzewa;

    public Drzewo(boolean wiecznieZielone, int wysokosc, String przekrojDrzewa){
        this.wiecznieZielone=wiecznieZielone;
        this.wysokosc=wysokosc;
        this.przekrojDrzewa=przekrojDrzewa;
    }

    public String toString(){
        String a=this.wiecznieZielone?"Wiecznie zielone: prawda\n":"Wiecznie zielone: falsz\n";
        String b="Wysokosc: "+ String.valueOf(this.wysokosc)+"\n";
        String c="Przekroj drzewa: "+this.przekrojDrzewa+"\n";
        return a+b+c;
    }
}
