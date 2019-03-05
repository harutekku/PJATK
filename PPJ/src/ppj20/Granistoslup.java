package ppj20;

public class Granistoslup extends Trojkat{
    private double b;

    public Granistoslup(double a, double b){
        super(a);
        this.b=b;
    }

    public String toString() {
        String a="Pole powierzchni graniastoslupa: "+String.valueOf(super.pole()*2+super.bokA()*this.b*3)+"\n";
        String b="Objetosc graniastoslupa: "+String.valueOf(super.pole()*this.b)+"\n";
        return a+b;
    }
}
