package ppj16;

public class Walec {
    public double promien;
    public double wysokosc;
    public Walec(double promien, double wysokosc){
        this.promien=promien;
        this.wysokosc=wysokosc;
    }
    public void show(){
        System.out.println("Pole powierzchni podstawy: \n" + (Math.PI*promien*promien) + "\nObjetosc walca:\n" + (Math.PI*promien*promien*wysokosc));
    }
}