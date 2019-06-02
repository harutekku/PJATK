package karol;

import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] z=new int[10];
        for(int i=0;i<z.length;i++){
            z[i]=(int)(Math.random()*22)-10;
        }

        System.out.println("zadanie 2");
        for (int value : z) {
            if (value >= 0) {
                System.out.print(value + ", ");
            }
        }
        System.out.println("\n");

        try {
            FileWriter plik=new FileWriter(args[0]);
            for(int value:z){
                if(value<0){
                    plik.write(value+" ");
                }
            }
            plik.flush();
        } catch (Exception e) {
            System.err.println("Nie podano argumentu nazwy pliku");
            System.out.println();
        }

        System.out.println("zadanie 4");
        System.out.println("Podaj trzy liczby rzeczywiste");
        double a,b,c,d;
        Scanner scanner=new Scanner(System.in);
        a=scanner.nextDouble();
        b=scanner.nextDouble();
        c=scanner.nextDouble();
        d=(double)((int)(a*b*c*100))/100.0;
        System.out.println(d);
        System.out.println();


        System.out.println("zadanie 5");
        int suma=0;
        for(int value:z){
            if(value>z[z.length-1]){
                suma+=value;
            }
        }
        System.out.println(suma);
        System.out.println();


        System.out.println("zadanie 6\nPodaj tekst");
        scanner=new Scanner(System.in);
        String tekst=scanner.nextLine();
        tekst=tekst.substring(0,tekst.length()-1)+'?';
        if(tekst.length()>3){
            System.out.println("Podaj indeks znaku");
            int i=scanner.nextInt();
            if(i>=tekst.length()){
                System.out.println("Nie ma takiego indeksu");
            }
            else{
                System.out.println(tekst.substring(i));
            }
        }
        System.out.println();
        char[][] chars=new char[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                chars[i][j]=(i+j)%2==0?'#':'?';
            }
        }
        System.out.println("zadanie 8");
        for(char[] wiersz:chars){
            for(char znak:wiersz){
                System.out.print(znak);
            }
            System.out.println();
        }
    }
}
