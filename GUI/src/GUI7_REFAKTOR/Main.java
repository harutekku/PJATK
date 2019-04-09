package GUI7_REFAKTOR;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random ra=new Random();
    static List<Figury> figury=new ArrayList<>();

    private static void generujFigury(int n){
        for(int i=0;i<n;i++){
            int random=ra.nextInt(3);
            if(random==0){
                //figury.add(new Owal());
            }
            else if(random==1){
                figury.add(new Poly());
            }
            else{
                //figury.add(new Rectangle());
            }
        }
        System.out.println("Done");
    }
/*
    private static void odczytaj(File file) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        boolean eof=false;
        while(!eof){
            Enums e=Enums.getEnum(in.readInt());

        }
    }
*/

    public static void main(String[] args) {
        System.out.println("Jaką akcje chcesz wykonać?");
        System.out.println("1: Wygeneruj nowe kształty i zapisz do pliku");
        System.out.println("2: Wczytaj kształty z pliku");
        System.out.println("3: Wygeneruj nowe kształty bez zapisywania");
        System.out.println("0: Przerwij program");

        int wybor= new Scanner(System.in).nextInt();
        switch (wybor){
            case 1:
                System.out.println("Ile kształtów chcesz wygenerować?");
                generujFigury(new Scanner(System.in).nextInt());
                try {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("figury.dat"))));
                    for(int i=0;i<figury.size();i++){
                        figury.get(i).write(dos);
                    }
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("figury.dat"))));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Ile kształtów chcesz wygenerować?");
                generujFigury(new Scanner(System.in).nextInt());
                break;
            case 0: default:
                return;
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame();
            }
        });

        return;
    }
}
