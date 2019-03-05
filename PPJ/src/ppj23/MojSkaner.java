package ppj23;

import java.io.IOException;
import java.io.InputStream;

public class MojSkaner {
    private InputStream skaner;

    public MojSkaner(InputStream skaner){
        this.skaner=skaner;
    }

    public String odczytajSlowo(){
        String slowo="";
        try{
            int a;
            while(!Character.isWhitespace(a=this.skaner.read())){
                slowo+=(char)(a);
                //System.out.println((char)(a)+" "+slowo);
            }
        }catch (IOException blad){
            System.out.println(blad);
        }
        return slowo;
    }

    public String odczytajLinie(){
        String slowo="";
        try{
            int a;
            while((a=this.skaner.read())!='\n'){
                slowo+=(char)(a);
                //System.out.println((char)(a)+" "+slowo);
            }
        }catch (IOException blad){
            System.out.println(blad);
        }
        return slowo;
    }

    public int odczytajLiczbeCalkowita(){
        int liczba=0;
        try{
            int a;
            boolean czyUjemna=false;
            while(!Character.isWhitespace(a=this.skaner.read())&&a!='.'&&a!=','){
                if(a=='-')czyUjemna=true;
                else{
                    liczba*=10;
                    liczba+=a;
                    liczba-='0';
                }
            }
            if(czyUjemna)liczba*=-1;
        }catch (IOException blad){
            System.out.println(blad);
        }
        return liczba;
    }

    public int odczytajDodatniaLiczbeCalkowita() throws Exception{
        int liczba=0;
        try{
            int a;
            while(!Character.isWhitespace(a=this.skaner.read())&&a!='.'&&a!=','){
                if(a=='-')throw new Exception("Liczba nie jest dodatnia");
                else{
                    liczba*=10;
                    liczba+=a;
                    liczba-='0';
                }
            }
        }catch (IOException blad){
            System.out.println(blad);
        }
        return liczba;
    }


    public static void main(String[] args){
        MojSkaner skanerek=new MojSkaner(System.in);
        //System.out.println(skanerek.odczytajDodatniaLiczbeCalkowita());
    }
}
