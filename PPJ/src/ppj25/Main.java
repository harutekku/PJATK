package ppj25;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static String doBinarkiOdwrotnej(int liczba){
        String binara="";
        while(liczba>0){
            if(liczba%2==1){
                binara+="1";
            }
            else{
                binara+="0";
            }
            liczba/=2;
        }
        while(binara.length()<8){
            binara+="0";
        }
        return binara;
    }
    public static String zlozString(String a, String b, String c, String d){
        String e=a+b+c+d;
        return e;
    }
    public static double zlozLiczbe(String a){
        double liczba=0;
        for(int i=31;i>=0;i--){
            liczba*=2;
            if(a.charAt(i)=='1'){
                liczba++;
            }
        }
        return liczba;
    }
    public static void main(String[] args){
        try{
            FileInputStream fis=new FileInputStream("src\\ppj25\\zad1.txt");
            StringBuffer sb=new StringBuffer();
            int suma=0;
            int wrt = fis.read();
            while(wrt != -1){
                //System.out.println(wrt);
                sb.append((char)wrt);
                wrt=fis.read();
            }
            StringTokenizer token=new StringTokenizer(sb.toString()," ");
            while(token.hasMoreElements()){
                suma+=Integer.parseInt(token.nextToken());
            }
            fis.close();



            System.out.println("suma: "+suma);
            System.out.println();
            System.out.println();
        }catch (IOException e){
            System.out.println(e);
        }




        try{
            FileInputStream fis=new FileInputStream("P:\\FTP(Public)\\knopers\\PPJ\\input\\z2_s18688.bin"); //FTP(Public) trzeba dodac jak sie na uczelni odpala
            StringBuffer sb=new StringBuffer();
            int liczba1=fis.read(), liczba2=fis.read(), liczba3=fis.read(), liczba4=fis.read();
            for(int i=0;i<=4;i++){
                //sb.append(wrt);
                //System.out.print(wrt+" "+doBinarkiOdwrotnej(wrt)+"\n");
                System.out.println("liczba: "+zlozLiczbe(zlozString(doBinarkiOdwrotnej(liczba1),doBinarkiOdwrotnej(liczba2),doBinarkiOdwrotnej(liczba3),doBinarkiOdwrotnej(liczba4))));
                //System.out.println(liczba1);
                System.out.println("Kod binarny liczby: "+doBinarkiOdwrotnej(liczba1)+doBinarkiOdwrotnej(liczba2)+doBinarkiOdwrotnej(liczba3)+doBinarkiOdwrotnej(liczba4));
                System.out.println();
                liczba1=fis.read();
                liczba2=fis.read();
                liczba3=fis.read();
                liczba4=fis.read();
            }



            System.out.println();
            System.out.println();


        }catch (IOException e){
            System.out.println(e);
        }




        try{
            FileInputStream fis1=new FileInputStream("src\\ppj25\\zad3-1.txt");
            FileInputStream fis2=new FileInputStream("src\\ppj25\\zad3-2.txt");
            FileInputStream fis3=new FileInputStream("src\\ppj25\\zad3-3.txt");
            int wrt1=fis1.read(),wrt2=fis2.read(),wrt3=fis3.read();
            while(wrt1!=-1 || wrt2!=-1 || wrt3!=-1){
                //sb.append((char)wrt);
                if(wrt1!=-1){
                    System.out.print((char)wrt1);
                }
                if(wrt2!=-1){
                    System.out.print((char)wrt2);
                }
                if(wrt3!=-1){
                    System.out.print((char)wrt3);
                }

                wrt1=fis1.read();
                wrt2=fis2.read();
                wrt3=fis3.read();
            }
            System.out.println();
            System.out.println();

        }catch (IOException e){
            System.out.println(e);
        }




        try{
            FileInputStream fis=new FileInputStream("src\\ppj25\\zad4.txt");
            StringBuffer sb=new StringBuffer();
            int wrt;
            while((wrt=fis.read())!=-1){
                sb.append((char)wrt);
                //System.out.print((char)wrt);

            }
            System.out.println();
            System.out.println();
            StringTokenizer token=new StringTokenizer(sb.toString());
            double liczba1, liczba2, wynik=0;
            String znak;
            FileWriter fw = new FileWriter("src\\ppj25\\zad4.txt", true);
            //fw.write("\n");
            while(token.hasMoreElements()){
                liczba1=Integer.parseInt(token.nextToken());
                //System.out.print(liczba1);

                znak=token.nextToken();
                //System.out.println(znak);

                liczba2=Integer.parseInt(token.nextToken());
                //System.out.print(liczba2);


                if(znak.equals("+")){
                    wynik=liczba1+liczba2;
                }
                else if(znak.equals("-")){
                    wynik=liczba1-liczba2;
                }
                else if(znak.equals("*")){
                    wynik=liczba1*liczba2;
                }
                else if(znak.equals("/")){
                    wynik=liczba1/liczba2;
                }
                else System.out.println("cos nie tak");
                //fw.write(liczba1+" "+znak+" "+liczba2+" = "+wynik+"\n");
                //System.out.println(wynik);
            }
            fw.flush();
            fw.close();
            System.out.println();
            System.out.println();

        }catch (IOException e){
            System.out.println(e);
        }

    }
}
