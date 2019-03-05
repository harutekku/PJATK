package ppj20;

public class Ostroslup extends Trojkat{
    private double b;

    public Ostroslup(double a, double b){
        super(a);
        this.b=b;
    }
    private double wysokoscScianyBocznej(){
        double c=this.b*this.b+super.wysokoscTrojkata()*super.wysokoscTrojkata();
        return Math.sqrt(c);
    }
    public String toString() {
        String a="Pole powierzchni ostroslupa: "+String.valueOf(super.pole()+super.bokA()*this.wysokoscScianyBocznej()*3)+"\n";
        String b="Objetosc ostroslupa: "+String.valueOf(super.pole()*this.b/3)+"\n";
        return a+b;
    }
}
