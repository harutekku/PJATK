import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

public class Main {

    public static void najdluzszyPodciagMan(String s1, String s2){
        long start = System.nanoTime();
        String[] nowe=noweSlowa(s1,s2);
        String news1=nowe[0],news2=nowe[1];

        String wynik1=szukaj(news1, news2);
        String wynik2=szukaj(news2, news1);
        String wynik0;
        if(wynik1.length()>wynik2.length())wynik0=wynik1;
        else wynik0=wynik2;
        long czas=System.nanoTime()-start;
        System.out.println("Czas wykonania: "+czas+" ("+ ((double)czas/1000000000)+" sekund)");
        System.out.println("Podane slowa: "+s1+" "+s2+"\nNowe slowa: "+news1+" "+news2+"\nNajdluzszy podciag to: "+wynik0+"\nI ma dlugosc "+wynik0.length());
    }
    public static String[] noweSlowa(String s1, String s2){
        StringBuilder news1=new StringBuilder(), news2=new StringBuilder();
        for(int i=0;i<s1.length();i++){
            if(czyJest(s1.charAt(i),s2)){
                news1.append(s1.charAt(i));
            }
        }
        for(int i=0;i<s2.length();i++){
            if(czyJest(s2.charAt(i),s1)){
                news2.append(s2.charAt(i));
            }
        }
        String[] wynik={news1.toString(),news2.toString()};
        return wynik;
    }
    public static boolean czyJest(char a, String b){
        for(int i=0;i<b.length();i++){
            if(b.charAt(i)==a)return true;
        }
        return false;
    }

    public static String szukaj(String a, String b){
        String wynik="";
        int ib=0;
        for(int ia=0;ia<a.length();ia++){
            int itempb=ib;
            while(itempb<b.length()){
                if(a.charAt(ia)==b.charAt(itempb)){
                    wynik+=a.charAt(ia);
                    ib=itempb+1;
                    break;
                }
                itempb++;
            }
        }
        return wynik;
    }
    public static void taras(String s1, String s2) {
        long start = System.nanoTime();
        int s1_l=s1.length();
        int s2_l=s2.length();
        StringBuilder newString1;
        String final1="";
        String final1_tmp;
        for (int index=0;index<s1_l;index++) {
            int tmp = 0;
            newString1 = new StringBuilder();
            for (int i = index; i < s1_l; i++) {
                for (int j = tmp; j < s2_l; j++) {
                    if (s1.charAt(i) == s2.charAt(j)) {
                        newString1.append(s1.charAt(i));
                        tmp = j + 1;
                        break;
                    }
                }
                if (newString1.length() == s1_l || newString1.length() == s2_l) {
                    break;
                }
            }
            final1_tmp = newString1.toString();
            if(final1.length() < final1_tmp.length()){
                final1 = final1_tmp;
                final1_tmp = "";
            }
        }
        long czas=System.nanoTime()-start;
        System.out.println("Czas wykonania: "+czas+" ("+ ((double)czas/1000000000)+" sekund)");
        System.out.println("Podane slowa: "+s1+" "+s2+"\nNajdluzszy podciag to: "+final1+"\nI ma dlugosc "+final1.length());
    }

    public static void rekurencyjnie(String s, ArrayList[] tab, int len){
        if(s.length()>len){
            //System.out.print(s+" ");
            rekurencyjnie(s.substring(1),tab,len);
            for(int i=1;i<s.length();i++){
                if(s.charAt(i)==s.charAt(i-1))continue;
                rekurencyjnie(s.substring(0,i)+s.substring(i+1),tab,len);
            }
        }
        else{
            //if(tab[s.length()]==null)tab[s.length()]=new ArrayList();
            if(s.length()==2){
                tab[1].add(s);
                //System.out.print(s+"; ");
            }
            else{
                tab[s.length()-1].add(s);
                //System.out.print(s+" ");
                rekurencyjnie(s.substring(1),tab,len);
                for(int i=1;i<s.length();i++){
                    if(s.charAt(i)==s.charAt(i-1))continue;
                    rekurencyjnie(s.substring(0,i)+s.substring(i+1),tab,len);
                }
            }
        }

    }

    public static String najdluzszyZSumy(ArrayList<String>[] a, ArrayList<String>[] b, int len){
        String najdluzszeSlowo="";
        for(int i=len-1;i>=2;i--){
            for(String s1:a[i]){
                for(String s2:b[i]){
                    if(s1.equals(s2)){
                        return s1;
                    }
                }
            }
        }
        return najdluzszeSlowo;
    }

    public static void najdluzszyPodciagRek(String s1, String s2){
        long start = System.nanoTime();
        String[] nowe=noweSlowa(s1,s2);
        String news1=nowe[0],news2=nowe[1];
        if(news1.length()!=0&&news2.length()!=0) {
            int new1len = news1.length(), new2len = news2.length();
            int len = Math.min(new1len, new2len);
            ArrayList[] listaA = new ArrayList[len];
            Arrays.setAll(listaA, element -> new ArrayList());
            ArrayList[] listaB = new ArrayList[len];
            Arrays.setAll(listaB, element -> new ArrayList());
            //ArrayList<ArrayList> listaB=new ArrayList<>(len);

            System.out.print("Rekurencja 1 ");
            rekurencyjnie(news1.toString(), listaA, len);
            System.out.print("Rekurencja 2 ");
            rekurencyjnie(news2.toString(), listaB, len);
            System.out.print("Sumowanie\n");
            String wynik0 = najdluzszyZSumy(listaA, listaB, len);
            long czas=System.nanoTime()-start;
            System.out.println("Czas wykonania: "+czas+" ("+ ((double)czas/1000000000)+" sekund)");
            System.out.println("Podane slowa: " + s1 + " " + s2 + "\nNowe slowa: " + news1 + " " + news2 + "\nNajdluzszy podciag to: " + wynik0 + "\nI ma dlugosc " + wynik0.length());
        }
        else{
            System.out.println("Czas wykonania: " + (System.nanoTime() - start));
            System.out.println("Podane slowa: " + s1 + " " + s2 + "\nNowe slowa: " + news1 + " " + news2 + "\nNajdluzszy podciag nie istnieje\nWiec jego dlugosc to 0");
        }
    }

    public static void podejscie2(String s1, String s2){
        long start = System.nanoTime();
        String[] nowe=noweSlowa(s1,s2);
        String news1=nowe[0],news2=nowe[1];

        String wynik=rekurencja2(news1,news2);

        long czas=System.nanoTime()-start;
        System.out.println("Czas wykonania: "+czas+" ("+ ((double)czas/1000000000)+" sekund)");
        System.out.println("Podane slowa: "+s1+" "+s2+"\nNowe slowa: "+news1+" "+news2+"\nNajdluzszy podciag to: "+wynik+"\nI ma dlugosc "+wynik.length());
    }
    public static String rekurencja2(String s1, String s2){
        String najdluszaKompilacja="";
        for(int i=0;i<s1.length();i++){
            long count=0;
            for(int j=0;j<s2.length();j++){
                if(s1.charAt(i)==s2.charAt(j))count++;
            }
            if(count>0){
                String[] kombinacje=new String[((int) count)];
                int numerKombinacji=0;
                for(int j=0;j<s2.length();j++){
                    if(s1.charAt(i)==s2.charAt(j)){
                        //System.out.println("test");
                        kombinacje[numerKombinacji]=s1.charAt(i)+rekurencja2(s1.substring(i+1),s2.substring(j+1));
                        numerKombinacji++;
                    }
                }
                for(String a:kombinacje){
                    //System.out.println(a);
                    if(a.length()>najdluszaKompilacja.length())najdluszaKompilacja=a;
                }
            }
        }
        return najdluszaKompilacja;
    }
    public static void main(String[] args) {
        String a1="afbabfzdc",b1="bacgbad";
        String a2="aggtab",b2="gxtxayb";
        String a3="aaaa",b3="aa";
        String a4="",b4="...";
        String a5="abba",b5="abcaba";
        String a6="BASSADBKLVFKLASBDKJFASDVJFHNASODVFYNASDOIUFYBASDIOUVFBYASIOUCBFYASIUODBYFVCASIOUDYFCASBOIFYAOSBDIUCB", b6="ODSIYBFCASDYBFOAUISDBYFIAUOCSDYBCOEWUIYRIQUWEBYOQRIUDOHQIYOYOIWURHYQWIOEUDRHYQOWIUEDRHYQOWIUYASOVBDBFOASDIFBOCASID";
        String a7="abeeeeab",b7="baeeeeba";
        String a8="abegehvgvmvhvmvevmvhvmvgvmvhvmvehgab",b8="baoioeoiouoioeoiouoioeoiouoioeoiouoioba";
        String a9="abcccccccc",b9="abc";
        String a10="abcdefghijk",b10="abcdefghijk";
        String a11="abcdeabcde",b11="abcde";
        String a12="abcdefghijklmnoprstuwxyz",b12="abcdefghijklmnoprstuwxyz";
        int [] tab={1,2,3,4};


        String a0=a12;
        String b0=b12;

        System.out.println();
        System.out.println();
        najdluzszyPodciagMan(a0,b0);
        System.out.println();
        System.out.println();
        taras(a0,b0);
        System.out.println();
        System.out.println();
        //najdluzszyPodciagRek(a0,b0);
        System.out.println();
        System.out.println();
        podejscie2(a0,b0);
    }
}