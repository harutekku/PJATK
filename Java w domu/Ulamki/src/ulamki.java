import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ulamki {
    private int licznik;
    private int mianownik;

    public ulamki(int licz, int mian){
        if(mian==0){
            System.out.println("Nie mozna dzielic przez zero");
        }
        else if(licz==0){
            licznik=0;
            mianownik=1;
        }
        else{
            int skrocenie=nwd(licz,mian);
            //System.out.println("skr "+skrocenie);
            licznik=skrocenie<0? licz*-1/skrocenie:licz/skrocenie;
            mianownik=skrocenie<0? mian*-1/skrocenie:mian/skrocenie;
        }
    }

    public static int nwd(int a, int b){
        boolean ujemna=false;
        if(a<0&&b<0){
            ujemna=false;
            //System.out.println("a");
        }
        else if(a<0){
            a*=-1;
            ujemna=true;
            //System.out.println("b");
        }
        else if(b<0){
            b*=-1;
            ujemna=true;
            //System.out.println("c");
        }

        int c=a%b;
        while(c>0){
            a=b;
            b=c;
            c=a%b;
        }
        return ujemna?b*-1:b;
    }

    public String toString() {
        if(this.licznik==0){
            return "0";
        }
        else{
            return this.licznik+"/"+this.mianownik;
        }
    }

    public String wartosc(){
        if(this.licznik==0) return "0";
        return String.valueOf((double)licznik/mianownik);
    }

    public String okres(){
        String rozwiniecie=this.wartosc();
        if(rozwiniecie.length()<10)return "Ulamek nie jest okresowy";
        boolean znaleziono;
        int ipocz,itemp;
        for(ipocz=2;ipocz<rozwiniecie.length()-1;ipocz++){
            itemp=ipocz;
            znaleziono=false;
            while(znaleziono==false&&itemp<rozwiniecie.length()-1){
                itemp++;
                if(rozwiniecie.charAt(itemp)==rozwiniecie.charAt(ipocz)){
                    znaleziono=true;
                    //System.out.println("znalazlem podejrzanego");
                }
                //System.out.println("odleglosc rosnie");
            }
            if(znaleziono){
                //System.out.println("Znalazlem");
                int odleglosc=itemp-ipocz;
                //System.out.println(ipocz+" "+itemp);
                boolean znalezionoPelen=true;
                for(int i=0;i<odleglosc;i++){
                    if(rozwiniecie.charAt(ipocz+i)!=rozwiniecie.charAt(itemp+i)){
                        znalezionoPelen=false;
                        //System.out.println("taki wal");
                    }
                }

                if(znalezionoPelen){
                    return rozwiniecie.substring(0,ipocz)+"("+rozwiniecie.substring(ipocz,ipocz+odleglosc)+")";
                }
            }

        }
        return "Ulamek nie jest okresowy";
    }

    public String pelna(){
        int a=this.okres().indexOf("(");
        String liczba=this.wartosc();
        if(a>1){
            int b=this.okres().indexOf(")");
            String okres=this.okres().substring(a+1,b);
            liczba=this.okres().substring(0,a);
            for(int i=1;i<=10;i++){
                liczba+=okres;
            }
        }
        return liczba;
    }

    public ulamki add(ulamki drugi){
        return new ulamki(this.licznik*drugi.mianownik+drugi.licznik*this.mianownik,this.mianownik*drugi.mianownik);
    }

    public ulamki substract(ulamki drugi){
        return new ulamki(this.licznik*drugi.mianownik-drugi.licznik*this.mianownik,this.mianownik*drugi.mianownik);
    }

    public ulamki multiply(ulamki drugi){
        return new ulamki(this.licznik*drugi.licznik,this.mianownik*drugi.mianownik);
    }

    public ulamki divide(ulamki drugi){
        return new ulamki(this.licznik*drugi.mianownik,this.mianownik*drugi.licznik);
    }

    public static String oblicz(String rownanie){
        int i=0;
        while(i<rownanie.length()){
            if(rownanie.charAt(i)=='('){
                int start=i;
                int a=1;
                while(a>0){
                    i++;
                    if(rownanie.charAt(i)=='('){
                        a++;
                    }
                    else if(rownanie.charAt(i)==')'){
                        a--;
                    }
                }
                System.out.println(oblicz(rownanie.substring(start+1,i)));
            }
            i++;
        }
        return rownanie;
    }
    /*public static ulamki majstruj(String rownanie){
        Pattern p=Pattern.compile("\\d+(\\\\)\\d+");
        Matcher m=p.matcher(rownanie);
    }*/
    public static void main(String[] args) {
        ulamki a=new ulamki(22,999);
        System.out.println(a);
        System.out.println(a.wartosc());
        System.out.println(a.okres());
        System.out.println(a.pelna());
        System.out.println();
        ulamki b=new ulamki(1,3);
        ulamki c=new ulamki(2,5);
        System.out.println(b.add(c));
        System.out.println(b.substract(c));
        System.out.println(b.multiply(c));
        System.out.println(b.divide(c));
        System.out.println();
        ulamki d=new ulamki(0,10);
        System.out.println(d);
        System.out.println();
        //System.out.println(oblicz("1/3+(2/3+(3/3+4/3))+(5/3+6/3)"));

    }

}
