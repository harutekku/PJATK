package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand=new Random();
    static List<Figures> figures =new ArrayList<>();
    static MyFrame frame;
    static boolean endOfProgram=false;

    private static void generateFigures(int n){
        for(int i=0;i<n;i++){
            figures.add(Figures.generateFigure());
        }
    }
    private static void betterChoice(){
        System.out.println(
                "Jaką akcje chcesz wykonać?\n" +
                "1: Wygeneruj nowe kształty i zapisz do pliku\n" +
                "2: Wczytaj kształty z pliku\n" +
                "3: Wygeneruj nowe kształty bez zapisywania\n" +
                "0: Przerwij program"
        );

        int userChoice=new Scanner(System.in).nextInt();
        switch (userChoice){
            case 1:
                System.out.println("Ile kształtów chcesz wygenerować?");
                generateFigures(new Scanner(System.in).nextInt());
                try {
                    long start=System.currentTimeMillis();
                    FileOperations.writeBin(new File("figures.dat"),figures);
                    long stop=System.currentTimeMillis();
                    System.out.println(stop-start);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    long start=System.currentTimeMillis();
                    FileOperations.readBin(new File("figures.dat"),figures);
                    long stop=System.currentTimeMillis();
                    System.out.println(stop-start);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Ile kształtów chcesz wygenerować?");
                generateFigures(new Scanner(System.in).nextInt());
                break;
            case 0: default:
                return;
        }
    }
    private static void worstChoice(){
        Thread th = new Thread(() -> {
            while(!endOfProgram){
                figures.add(Figures.generateFigure());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(figures.size()>=100)endOfProgram=true;
            }
        });
        th.start();
        return;
    }


    public static void main(String[] args) {

        // WYBOR DZIALANIA APLIKACJI, 0: TRYB STANDARDOWY, 1: TRYB INTERAKTYWNY
        int model=0;

        if(model==1)betterChoice();
        else worstChoice();


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame=new MyFrame();
            }
        });

        return;
    }
}
