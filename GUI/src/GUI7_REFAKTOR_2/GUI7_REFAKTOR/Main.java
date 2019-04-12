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
    static File toSave=new File("figures.dat");
    static File toRead=new File("figures.dat");

    public static void generateFigures(int n){
        for(int i=0;i<n;i++){
            figures.add(Figures.generateFigure());
        }
    }

    public static void betterChoice(){
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
                    FileOperations.writeBin(toSave,figures);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    FileOperations.readBin(toRead,figures);
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
    public static void worstChoice(){
        Thread th = new Thread(() -> {
            while(!endOfProgram){
                figures.add(Figures.generateFigure());
                try {
                    FileOperations.writeBin(toSave,figures);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        // WYBOR DZIALANIA APLIKACJI, 0: TRYB STANDARDOWY, 1: TRYB KONSOLOWY, 2: TRYB INTERAKTYWNY
        int model=0;

        // TODO: 2019-04-12 listDataListener


        if(model==0){
            try {
                FileOperations.readBin(toRead,figures);
            } catch (IOException e) {
                System.err.println("Nie znaleziono pliku");
            }
            worstChoice();
        }
        else if(model==1){
            betterChoice();
        }

        EventQueue.invokeLater(() -> frame=new MyFrame(model));




        return;
    }
}
