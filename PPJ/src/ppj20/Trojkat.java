package ppj20;

public class Trojkat {
    private double a;

    public Trojkat(double a){
        this.a=a;
    }
    public double pole(){
        return this.a*this.a*Math.sqrt(3)/4;
    }
    public double bokA(){
        return this.a;
    }
    public double wysokoscTrojkata(){
        return this.a*Math.sqrt(3)/2;
    }
    public String toString() {
        String a="Pole powierzchni trojkata: "+String.valueOf(this.pole())+"\n";
        return a;
    }
}
