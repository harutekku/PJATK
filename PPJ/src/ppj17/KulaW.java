package ppj17;

public class KulaW {
    public double promien;
    public KulaW(Kwadrat kwa){
        this.promien=kwa.bok/2;
    }
    public KulaW(Walec wal){
        this.promien=(wal.promien<=(wal.wysokosc/2))?wal.promien:wal.wysokosc/2;
    }
    public void showPromien(){
        System.out.println("Promien: "+this.promien);
    }
}
