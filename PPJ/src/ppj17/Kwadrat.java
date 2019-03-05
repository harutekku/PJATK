package ppj17;

public class Kwadrat {
    public int bok;
    public double promienWalca;
    public Kwadrat(int bok){
        this.bok=bok;
        this.promienWalca=bok/2;
    }
    public void show(){
        System.out.println("Pole powierzchni: \n" + (bok*bok) + "\nObjetosc szescianu:\n" + (bok*bok*bok));
        //System.out.println("Pole powierzchni podstawy walca: \n" + (Math.PI*promienWalca*promienWalca) + "\nObjetosc walca:\n" + (Math.PI*promienWalca*promienWalca*(double)(bok)));
    }
}