package GUI7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
    // WYBOR DZIALANIA APLIKACJI, 0: TRYB STANDARDOWY, 1: TRYB KONSOLOWY, 2: TRYB GRAFICZNY, 3: CZYŚĆ PLIK
    static int model=2;

    // MAKSYMALNA ILOŚĆ FIGUR, PO JEJ OSIĄGNIĘCIU PROGRAM NIE GENERUJE NOWYCH, 0 DLA NIELIMITOWANEGO
    static int maxNumberOfFigures=0;

    // CZAS POMIĘDZY GENEROWANIEM NOWYCH OBIEKTOW
    static int time=1000;



    static Random rand=new Random();
    static List<Figures> figures=new ArrayList<>();
    static MyFrame frame;
    static boolean endOfProgram=false;
    static File file =new File("figures.dat");


    public static void main(String[] args) {
        switch (model){
            case 0:
                MainMenu.readFile();
                MainMenu.standardMode();
                break;
            case 1:
                MainMenu.consoleMode();
                break;
            case 2:
                MainMenu.graphicalMode();
                break;
            case 3:
                FileOperations.clearFile(file);
                break;
            default:
                System.err.println("Zły model");
                break;
        }
    }
}
