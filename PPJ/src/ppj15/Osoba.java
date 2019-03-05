/**
 * Osoba
 */
public class Osoba {
    public String imie;
    public String nazwisko;
    public int rok_urodzenia;

    public Osoba(String imie, String nazwisko, int rok) {
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.rok_urodzenia=rok;
    }
    public void show() {
        System.out.println("imie: "+this.imie+"\nnazwisko: "+this.nazwisko+"\nRok: "+this.rok_urodzenia);
    }
}