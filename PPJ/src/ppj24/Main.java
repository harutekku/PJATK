package ppj24;

import java.io.*;

public class Main {
    private static boolean czyPierwsza(int liczba){
        if(liczba%2==0)return false;
        else{
            for(int i=3;i*i<=liczba;i+=2){
                if(liczba%i==0)return false;
            }
            return true;
        }
    }
    private static int generujPierwsza(){
        int x=2;
        while(!czyPierwsza(x)){
            x=(int)(Math.random()*100);
        }
        return x;

    }
    private static boolean tablicaCzyPierwsza(int[] tab){
        for(int i=0;i<tab.length;i++){
            if(czyPierwsza(tab[i])==false)return false;
        }
        return true;
    }

    public static void main(String[] args){
        try{
            FileWriter fw=new FileWriter("pierwsze.txt");
            for(int i=0;i<20;i++){
                fw.write(String.valueOf(generujPierwsza()));
                fw.write("\n");
            }
            fw.flush();
            fw.close();


            BufferedReader br=new BufferedReader(new FileReader("pierwsze.txt"));
            int[] tab=new int[20];
            int index=0;
            String wrt;
            while((wrt = br.readLine()) != null){
                tab[index]=Integer.parseInt(wrt);
                index++;
            }

            br.close();

            System.out.println(tablicaCzyPierwsza(tab)+" "+index);

        }catch (IOException e){
            System.out.println(e);
        }
    }
}
