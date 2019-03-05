import java.io.FileWriter;

public class DobraRekurencja {
    public static void podejscie2(String s1, String s2, FileWriter fos)throws Exception{
        String[] nowe=noweSlowa(s1,s2);
        String news1=nowe[0],news2=nowe[1];
        long start = System.nanoTime();
        //String wynik=rekurencja2(news1,news2);
        String wynik=rekurencja2(s1,s2);
        long czas=System.nanoTime()-start;
        //System.out.println("Czas wykonania: "+czas+" ("+ ((double)czas/1000000000)+" sekund)");
        //System.out.println("Podane slowa: "+s1+" "+s2+"\nNowe slowa: "+news1+" "+news2+"\nNajdluzszy podciag to: "+wynik+"\nI ma dlugosc "+wynik.length());
        //fos.write(czas+";"+news1+";"+news2+"\n");
        fos.write(czas+";"+s1+";"+s2+"\n");
    }

    public static String rekurencja2(String s1, String s2){
        if(s1.length()==s2.length()&&s1.length()==1&&s1.charAt(0)==s2.charAt(0))return s1;
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
                        kombinacje[numerKombinacji]=s1.charAt(i)+rekurencja2(s1.substring(i+1),s2.substring(j+1));
                        numerKombinacji++;
                    }
                }
                for(String a:kombinacje){
                    if(a.length()>najdluszaKompilacja.length())najdluszaKompilacja=a;
                }
            }
        }
        return najdluszaKompilacja;
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
    public static String[][] generator(int ileRazyKazdaDlugosc, int maksDlugosc){
        String[][] tab=new String[ileRazyKazdaDlugosc*maksDlugosc][2];
        int index=0;
        for(int i=0;i<maksDlugosc;i++){
            for(int j=0;j<ileRazyKazdaDlugosc;j++){
                tab[index][0]="";
                tab[index][1]="";
                for(int k=i;k>0;k--){
                    tab[index][0]+=(char)((Math.random()*26)+'A');
                    tab[index][1]+=(char)((Math.random()*26)+'A');
                }
                index++;
            }
            //System.out.println(i+" "+tab[i][0]+" "+tab[i][1]);
        }
        return tab;
    }

    public static void main(String[] args){
        try{
            FileWriter fos=new FileWriter("obliczanieGenerator2.txt");
            /*String s="abcdefghijklmnoprstuwxyzAB";
            for(int i=25;i>=0;i--){
                podejscie2(s.substring(i),s.substring(i),fos);
            }
            s="aaaaaaaaaaaaaaaaaaaaaaaaaa";
            fos.write("\n\n");
            for(int i=25;i>=0;i--){
                podejscie2(s.substring(i),"a",fos);
            }
            fos.write("\n\n");
            s="aaaaaaaaaaaaaaaa";
            for(int i=15;i>=0;i--){
                podejscie2(s.substring(i),s.substring(i),fos);
            }*/


            /*String a="a";
            for(int i=0;i<1000;i++){
                podejscie2(a,"a",fos);
                a+="a";
            }*/


            String[][] slownik=generator(10000,50);
            //System.out.println(slownik[1][0]+" "+slownik[1][1]);
            for(int i=0;i<slownik.length;i++){
                //System.out.println(slownik[i][0]+" "+slownik[i][1]);
            }
            for(int i=0;i<slownik.length;i++){
                podejscie2(slownik[i][0],slownik[i][1],fos);
                if(i%1000==0) System.out.println(i);
            }

            fos.flush();
            fos.close();




        }catch(Exception e){
            System.out.println(e);
        }


    }
}
