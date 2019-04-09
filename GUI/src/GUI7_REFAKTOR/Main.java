package GUI7_REFAKTOR;

import org.w3c.dom.events.Event;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random ra=new Random();
    static List<Figury> figury=new ArrayList<>();
    static boolean endOfProgram=false;
    static MyFrame frame;


    private static void generujFigury(int n){
        for(int i=0;i<n;i++){
            figury.add(Figury.generujFigure());
        }
    }
    private static void betterChoice(){
        System.out.println("Jaką akcje chcesz wykonać?");
        System.out.println("1: Wygeneruj nowe kształty i zapisz do pliku");
        System.out.println("2: Wczytaj kształty z pliku");
        System.out.println("3: Wygeneruj nowe kształty bez zapisywania");
        System.out.println("0: Przerwij program");

        int wybor=new Scanner(System.in).nextInt();
        switch (wybor){
            case 1:
                System.out.println("Ile kształtów chcesz wygenerować?");
                generujFigury(new Scanner(System.in).nextInt());
                try {
                    long start=System.currentTimeMillis();
                    writeBin(new File("figury.dat"),figury);
                    long stop=System.currentTimeMillis();
                    System.out.println(stop-start);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    long start=System.currentTimeMillis();
                    readBin(new File("figury.dat"),figury);
                    long stop=System.currentTimeMillis();
                    System.out.println(stop-start);
                } catch (IOException e) {
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
    }
    private static void worstChoice(){
        Thread th = new Thread(() -> {
            while(!endOfProgram){
                figury.add(Figury.generujFigure());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(figury.size()>=100)endOfProgram=true;
            }
        });
        th.start();
        return;
    }
    private static void writeBin(File file, List<Figury> lista) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        for(int i=0;i<figury.size();i++){
            lista.get(i).write(dos);
        }
        dos.flush();
        dos.close();
    }

    private static void readBin(File file, List<Figury> lista) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        boolean eof=false;
        while(!eof){
            try{
                Enums e=Enums.getEnum(in.read());
                switch (e){
                    case Owal:
                        Figury o=Owal.read(in);
                        lista.add(o);
                        break;
                    case Poly:
                        Figury p=Poly.read(in);
                        lista.add(p);
                        break;
                    case Rectangle:
                        Figury r=Rectangle.read(in);
                        lista.add(r);
                        break;
                    default:
                        eof=true;
                        System.out.println("Wczytano wszystko z pliku");
                        break;
                }
            }catch (EOFException e){
                eof=true;
            }
        }
        in.close();
    }

    public static void main(String[] args) {

        // WYBOR DZIALANIA APLIKACJI, 0: TRYB STANDARDOWY, 1: TRYB INTERAKTYWNY
        int model=1;



        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame=new MyFrame();
            }
        });

        if(model==1)betterChoice();
        else worstChoice();

        return;
    }
}
