package ppj20;

public class Prostokat {
    private double a;
    private double b;

    public Prostokat(double a, double b){
        this.a=a;
        this.b=b;
    }
    public double pole(){
        return this.a*this.b;
    }
    public double bokA(){
        return this.a;
    }
    public double bokB(){
        return this.b;
    }
    public String toString() {
        String a="Pole powierzchni prostokata: "+String.valueOf(this.pole())+"\n";
        return a;
    }
}
