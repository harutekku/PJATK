package GUI7;

import javax.swing.*;
import java.awt.*;

class MenuPanel extends JPanel {
    MenuPanel(int width, int height, MyFrame frame) {
        setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(500,200));
        setBackground(Color.BLACK);
        setLayout(new GridLayout(3,2));

        JButton standardMode=new JButton("Standardowy tryb");
        standardMode.addActionListener(e->{
            frame.enableDrawPanel(this, getWidth(), getHeight());
            MainMenu.readFile();
            MainMenu.standardMode();
        });

        JButton standardWithoutReading=new JButton("<html>Standardowy tryb bez<br />wczytywania i zapisania</html>");
        standardWithoutReading.addActionListener(e->{
            frame.enableDrawPanel(this, getWidth(), getHeight());
            MainMenu.standardModeWithoudWrite();
        });

        JButton read=new JButton("Wczytaj figury z pliku");
        read.addActionListener(e->{
            MainMenu.readFile();
            frame.enableDrawPanel(this, getWidth(), getHeight());
        });

        JButton generateNewFigures=new JButton("Wygeneruj nowe figury (20)");
        generateNewFigures.addActionListener(e->{
            Figures.generateFigures(20);
            System.out.println("Wygenerowano figury (20)");
            MainMenu.writeFile();
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