package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

class MainMenu {
    private static void standardModeWithoutReading(){
        if(Main.frame==null){
            EventQueue.invokeLater(() -> Main.frame=new MyFrame(Main.model));
        }
        while(Main.frame==null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread th = new Thread(() -> {
            while(!Main.endOfProgram){
                Figures tmp=Figures.generateFigure();
                Main.figures.add(tmp);
                Main.frame.drawPanel.drawFigure(tmp);
                try {
                    FileOperations.writeOneObjectBin(Main.file, tmp);
                    Thread.sleep(500);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    System.err.println("Błąd zapisu do pliku");
                }
                if(Main.maxNumberOfFigures!=0){
                    if(Main.figures.size()>Main.maxNumberOfFigures){
                        System.out.println("Osiągnięto limit figur");
                        Main.endOfProgram=true;
                    }
                }
            }
        });
        th.start();
    }

    static void standardMode(){
        try {
            FileOperations.readBin(Main.file, Main.figures);
        } catch (IOException e) {
            System.err.println("Nie znaleziono pliku do odczytu");
        }
        standardModeWithoutReading();
    }

    static void consoleMode(){
        System.out.println(
                "Jaką akcje chcesz wykonać?\n" +
                        "1: Uruchom program\n" +
                        "2: Uruchom program bez wczytania figur z pliku\n" +
                        "3: Wygeneruj nowe kształty i wyświetl\n" +
                        "4: Wygeneruj nowe kształty, wyświetl i zapisz do pliku\n" +
                        "5: Wczytaj kształty z pliku i wyświetl\n" +
                        "6: Wyczyść plik z danych\n" +
                        "0: Przerwij program\n"
        );

        int userChoice=new Scanner(System.in).nextInt();
        switch (userChoice){
            case 1:
                standardMode();
                break;
            case 2:
                standardModeWithoutReading();
                break;
            case 3:
                System.out.println("Ile kształtów chcesz wygenerować?");
                Figures.generateFigures(new Scanner(System.in).nextInt());
                EventQueue.invokeLater(() -> Main.frame=new MyFrame(Main.model));
                while(Main.frame==null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Main.frame.drawPanel.repaint();
                break;
            case 4:
                System.out.println("Ile kształtów chcesz wygenerować?");
                Figures.generateFigures(new Scanner(System.in).nextInt());
                EventQueue.invokeLater(() -> Main.frame=new MyFrame(Main.model));
                while(Main.frame==null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Main.frame.drawPanel.repaint();
                try {
                    FileOperations.writeAllBin(Main.file,Main.figures);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                try {
                    EventQueue.invokeLater(() -> Main.frame=new MyFrame(Main.model));
                    FileOperations.readBin(Main.file, Main.figures);
                    while(Main.frame==null){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Main.frame.drawPanel.repaint();
                } catch (IOException e) {
                    System.err.println("Błąd odczytu danych z pliku");
                }
                break;
            case 6:
                FileOperations.clearFile(Main.file);
                break;
            default:
                System.out.println("Zły wybór");
            case 0:
                System.out.println("Wyłączanie");
                break;
        }
    }

    static void graphicalMode(){
        EventQueue.invokeLater(() -> Main.frame=new MyFrame(Main.model));
    }
}
