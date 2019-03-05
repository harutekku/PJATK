package ppj20;

public class Prostopadloscian extends Prostokat{
    private double c;

    public Prostopadloscian(double a, double b, double c){
        super(a,b);
        this.c=c;
    }

    public String toString() {
        String a="Pole powierzchni prostopadloscianu: "+String.valueOf(super.pole()*2+super.bokA()*this.c*2+super.bokB()*this.c*2)+"\n";
        String b="Objetosc prostopadloscianu: "+String.valueOf(super.pole()*this.c)+"\n";
        return a+b;
    }
}
