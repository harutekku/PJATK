package ppj27;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Osoba {
    private String imie;
    private String nazwisko;
    private int rok_urodzenia;
    private boolean plec;
    private short kodPocztowy;

    public Osoba()throws NiewlasciweDaneException{
        System.out.println("Podaj dane w kolejnosci: imie, nazwisko, rok urodzenia, plec (mezczyzna=prawda), kod pocztowy\n");
        Scanner odczyt=new Scanner(System.in);
        try{
            imie=odczyt.next();
            nazwisko=odczyt.next();
            rok_urodzenia=odczyt.nextInt();
            plec=odczyt.nextBoolean();
            kodPocztowy=odczyt.nextShort();
        }catch (Exception ex){
            throw new NiewlasciweDaneException();
        }
    }

    public Osoba(String imie, String nazwisko, int rok, boolean plec, short kod)throws NiewlasciweDaneException{
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.rok_urodzenia=rok;
        this.plec=plec;
        this.kodPocztowy=kod;
    }
    public static void resetuj() throws IOException{
        FileOutputStream fos=new FileOutputStream("src\\ppj27\\Osoby.bin");
        //fos.write();
        fos.flush();
        fos.close();
    }
    public static void drukuj(Osoba a) throws IOException {
        FileOutputStream fos=new FileOutputStream("src\\ppj27\\Osoby.bin",true);
        String s=a.imie+" "+a.nazwisko+" "+a.rok_urodzenia+" "+a.plec+" "+a.kodPocztowy+"\n";
        //System.out.println(s);
        char[] im=a.imie.toCharArray();
        char[] naz=a.nazwisko.toCharArray();

        char[] c=s.toCharArray();
        byte[] d=s.getBytes();
        for(int i=0;i<c.length;i++){
            fos.write(c[i]);
        }
        fos.flush();
        fos.close();
    }

    public static void odczytaj() throws IOException{
        FileInputStream fis=new FileInputStream("src\\ppj27\\Osoby.bin");
        int wrt;
        while((wrt=fis.read())!=-1){
            System.out.print((char)(wrt));

        }
        fis.close();
    }

    public static void main(String[] args) {
        try{
            resetuj();
            Osoba a=new Osoba();
            //Osoba a=new Osoba("Adam","Kowalski",1992,true, ((short) 02222d));
            //Osoba b=new Osoba("Kasia","Kowalska",1993,false, ((short) 02329d));
            drukuj(a);
            //drukuj(b);
            odczytaj();
        }catch(NiewlasciweDaneException ex){
            System.out.println(ex);
        }catch (IOException ex){
            System.out.println(ex);
        }


    }
}
