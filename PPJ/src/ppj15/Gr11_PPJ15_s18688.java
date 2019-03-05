/**
 * Gr11_PPJ15_s18688
 */
public class Gr11_PPJ15_s18688 {
    public static void main(String[] args) {
        MethodCurrier method=new MethodCurrier();
        method.setValue((int)5);
        method.setValue((float)5);
        method.setValue((char)5);
        method.setValue((byte)5);
        //na podstawie tego jaka zmienna podajemy do metody

        Number a=new Number();
        a.setValue(12);
        method.setValue(a);
        
        
        Osoba robaczek=new Osoba("Tomek", "Bober", 1999);
        robaczek.show();

        Cplx pierwsza=new Cplx(12,3);
        Cplx druga=new Cplx(6,2);
        Cplx trzecia=new Cplx(1,7);
        pierwsza.show();
        pierwsza.add(druga);
        pierwsza.show();
        pierwsza.sub(trzecia);
        pierwsza.show();
        pierwsza.mul(druga);
        pierwsza.show();
        pierwsza.inc();
        pierwsza.show();

    }
    
}