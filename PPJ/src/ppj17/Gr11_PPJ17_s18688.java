package ppj17;

public class Gr11_PPJ17_s18688 {
    public static void main(String[] args){
        Kukurydza koko=new Kukurydza(100);
        Kwadrat kwa=new Kwadrat(10);
        Walec wal=new Walec(10,20);

        KulaW kulkaKwa=new KulaW(kwa);
        kulkaKwa.showPromien();

        KulaW kulkaWal=new KulaW(wal);
        kulkaWal.showPromien();


        KulaNa kulkaNaKwa=new KulaNa(kwa);
        kulkaNaKwa.showPromien();

        KulaNa kulkaNaWal=new KulaNa(wal);
        kulkaNaWal.showPromien();

        Osoba a=new Osoba("Bogdan", 2000);
        Osoba b=new Osoba("Rysio", 1989);
        Osoba c=new Osoba("Madzia");
        //sprawdzam cos
        Osoba.zwrocStarszaOsobe(a,c).dostarczImie();
        Osoba[] zbior={a,b,c};
        Osoba.zwrocNajstarszaOsobe(zbior).dostarczImie();
    }
}
