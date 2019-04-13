package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class MenuPanel extends JPanel {
    MenuPanel(int width, int height, MyFrame frame) {
        setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(500,200));
        setBackground(Color.BLACK);
        setLayout(new GridLayout(3,2));

        JButton standardMode=new JButton("Standardowy tryb");
        standardMode.addActionListener(e->{
            frame.enableDrawPanel(this, getWidth(), getHeight());
            MainMenu.standardMode();
        });

        JButton standardWithoutReading=new JButton("<html>Standardowy tryb bez<br />wczytywania i zapisania</html>");
        standardWithoutReading.addActionListener(e->{
            frame.enableDrawPanel(this, getWidth(), getHeight());
            Thread th = new Thread(() -> {
                while(!Main.endOfProgram){
                    Figures tmp=Figures.generateFigure();
                    Main.figures.add(tmp);
                    Main.frame.drawPanel.drawFigure(tmp);
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException ex) {
                        ex.fillInStackTrace();
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
        });

        JButton read=new JButton("Wczytaj figury z pliku");
        read.addActionListener(e->{
            try {
                FileOperations.readBin(Main.file, Main.figures);
                frame.enableDrawPanel(this, getWidth(), getHeight());
            } catch (IOException ex) {
                System.err.println("Błąd odczytu danych z pliku");
            }
        });

        JButton generateNewFigures=new JButton("Wygeneruj nowe figury (20)");
        generateNewFigures.addActionListener(e->{
            Figures.generateFigures(20);
            System.out.println("Wygenerowano figury (20)");
        });

        JButton clearFile=new JButton("Wyczyść plik");
        clearFile.addActionListener(e-> FileOperations.clearFile(Main.file));

        JButton exit=new JButton("Zakończ program");
        exit.addActionListener(e-> System.exit(0));

        add(standardMode);
        add(standardWithoutReading);
        add(read);
        add(generateNewFigures);
        add(clearFile);
        add(exit);
    }
}