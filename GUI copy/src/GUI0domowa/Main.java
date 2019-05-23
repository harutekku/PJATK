package GUI0domowa;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void evenNumbers(int[] tab){
        for(int i:tab){
            System.out.print(i+" ");
        }
    }

    public static void oddIndexes(int[] tab){
        for(int i=1;i<tab.length;i+=2){
            System.out.print(tab[i]+" ");
        }
    }

    public static void showDiagonals(int[][] tab){
        String a="",b="";
        for(int i=0;i<tab.length;i++){
            a+=tab[i][i]+" ";
            b+=tab[i][tab.length-1-i]+" ";
        }
        System.out.println(a+"\n"+b);
    }

    public static void showEdges(int[][] tab){
        String a="";
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[i].length;j++){
                a+=(i==0||i==tab.length-1||j==0||j==tab[i].length-1)?(tab[i][j]<10?"0"+tab[i][j]+",":tab[i][j]+","):"XX,";
            }
            a+="\n";
        }
        System.out.println(a);
    }


    public static void main(String[] args) throws NieprawidlowaKlasaException {
        //Zadanie 0
        int a = 5;
        char b = 'a';
        float c = 4f;

        //Zadanie 1
        //Wynikiem będzie "Tak", gdyż ponieważ b jest true

        //Zadanie 2

        int[] tab = new int[10];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = (int) (Math.random() * 8 + 3);
        }
//        evenNumbers(tab);
//        System.out.println();
//        oddIndexes(tab);

        //Zadanie 3

        /*int[][] tab2=new int[5][];
        for(int i=0;i<tab2.length;i++){
            tab2[i]=new int[(int)(Math.random()*3+3)];
            for(int j=0;j<tab2[i].length;j++){
                tab2[i][j]=(int)(Math.random()*21);
            }
        }

        for(int[] i:tab2) {
            for(int j:i){
                System.out.print(j+" ");
            }
            System.out.println();
        }*/

        //Zadanie 4

        int tab3[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int tab4[][] = {{1, 2, 3, 4, 5, 6}, {4, 5, 6, 7, 8, 9}, {7, 8, 9, 10, 11, 12}, {13, 14, 15, 16, 17, 18}, {19, 20, 21, 22, 23, 24}};

        /*showDiagonals(tab3);
        System.out.println();

        showDiagonals(tab4);
        System.out.println();

        showEdges(tab3);
        System.out.println();

        showEdges(tab4);*/


        //Zadanie 5
/*

        Osoba o1 = new Osoba(13, "Anna");
        Osoba o2 = new Osoba(15, "Jan");
        System.out.println(o1);
        System.out.println(o2);
*/

        //Zadanie 6
        /*Ksiazka e=new Ksiazka("haslo",100,"Roman");
        Ksiazka f=new Ksiazka("maslo",101,"Nie Roman");
        System.out.println(e+" "+f);
        Podrecznik g=new Podrecznik("abecadlo",102,"Kowal",1);
        System.out.println(g);


        Ksiazka k1 = new Ksiazka("Tytul_1", 45, "Anonim");
        Ksiazka k2 = new Podrecznik("Tytul_2", 34, "Anna Nowak",2);
        Podrecznik k3 = new Podrecznik("Tytul_3", 23, "Jan Kowalski",3);
        Ksiazka[] tab5 = {k1, k2, k3};
        for(Ksiazka k : tab5)System.out.println(k);
*/

        //Zadanie 7
        try {
            Scanner scan = new Scanner(new File("src\\GUI0domowa\\A.txt"));
            FileWriter fw = new FileWriter("src\\GUI0domowa\\B.txt");
            while(scan.hasNext()) {
                //String h=scan.next();
                //String i=scan.next();
                //double j=Double.valueOf(h)+Double.valueOf(i);
                if (scan.hasNextInt()) {
                    fw.write(String.valueOf(scan.nextInt() + scan.nextInt()));
                } else {
                    fw.write(String.valueOf(Double.valueOf(scan.next())+Double.valueOf(scan.next())));
                }
                fw.write("\n");
            }
            fw.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


class Osoba{
    private int wiek;
    private String imie;
    public Osoba(int wiek, String imie){
        this.wiek = wiek;
        this.imie = imie;
    }
    public String toString(){
        return this.imie + " " + this.wiek;
    }
}

class Ksiazka{
    private String tytul,autor;
    private int liczbaStron;
    public Ksiazka(){
        tytul="-NONE";
        autor="-BRAK-";
        liczbaStron=-1;
    }
    public Ksiazka(String tyt, int licz, String aut){
        tytul=tyt;
        autor=aut;
        liczbaStron=licz;
    }
    public String toString(){
        return tytul+" "+liczbaStron+" "+autor;
    }
}

class Podrecznik extends  Ksiazka{
    private int klasa;
    public Podrecznik(String tyt, int licz, String aut, int kla) throws NieprawidlowaKlasaException {
        super(tyt,licz,aut);
        if(kla<9&&kla>0)klasa=kla;
        else throw new NieprawidlowaKlasaException();
    }
    public String toString(){
        return super.toString()+" "+klasa;
    }
}

class NieprawidlowaKlasaException extends Exception{
    public NieprawidlowaKlasaException(){
        super();
    }
}
