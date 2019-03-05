package ppj17;

public class KulaNa {
    public double promien;
    public KulaNa(Kwadrat kwa){
        this.promien=kwa.bok*Math.sqrt(3);
    }
    public KulaNa(Walec wal){
        this.promien=Math.sqrt(Math.pow(wal.promien,2)+Math.pow(wal.wysokosc/2,2));
    }
    public void showPromien(){
        System.out.println("Promien: "+this.promien);
    }
}
