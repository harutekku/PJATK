package ppj29;

public class Wiadomosc {
    private int dzien, miesiac, rok;
    private String godzina, user, ip, tekst;

    public Wiadomosc(String dzien,String miesiac,String rok,String godzina,String user,String ip,String tekst){
        this.dzien=Integer.parseInt(dzien);
        this.miesiac=Integer.parseInt(miesiac);
        this.rok=Integer.parseInt(rok);
        this.godzina=godzina;
        this.user=user;
        this.ip=ip;
        this.tekst=tekst;
    }

    @Override
    public String toString() {
        return "wynik";
    }
}
