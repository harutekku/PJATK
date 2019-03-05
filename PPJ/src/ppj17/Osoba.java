package ppj17;

public class Osoba {
    public String imie;
    public int rok_urodzenia;
    public Osoba(String imie, int rok_urodzenia){
        this.imie=imie;
        this.rok_urodzenia=rok_urodzenia;
    }
    public Osoba(String imie){
        this.imie=imie;
        this.rok_urodzenia=1990;
    }
    public void dostarczImie(){
        System.out.println("imie: "+this.imie);
    }
    public void dostarczWiek(){
        System.out.println("Wiek: "+(2018-this.rok_urodzenia));
    }
    public static Osoba zwrocStarszaOsobe(Osoba a, Osoba b){
        return (a.rok_urodzenia<b.rok_urodzenia)?a:b;
    }
    public static Osoba zwrocNajstarszaOsobe(Osoba[] a){
        int najstarsza=0;
        for(int i=1;i<a.length;i++){
            if(a[i].rok_urodzenia<a[najstarsza].rok_urodzenia){
                najstarsza=i;
            }
        }
        return a[najstarsza];
    }
}
