/**
 * Cplx
 */
public class Cplx {
    private double rzeczywiste;
    private double urojone;

    public Cplx(double x, double i) {
        this.rzeczywiste=x;
        this.urojone=i;
    }
    public void add(Cplx a){
        this.rzeczywiste+=a.rzeczywiste;
        this.urojone+=a.urojone;
    }
    
    public void sub(Cplx a){
        this.rzeczywiste-=a.rzeczywiste;
        this.urojone-=a.urojone;
    }
    
    public void mul(Cplx a){
        this.rzeczywiste*=a.rzeczywiste;
        this.urojone*=a.urojone;
    }
    public void inc(){
        this.rzeczywiste++;
    }
    public void show(){
        System.out.println(this.rzeczywiste+" "+this.urojone+"i");
    }
    
    
}