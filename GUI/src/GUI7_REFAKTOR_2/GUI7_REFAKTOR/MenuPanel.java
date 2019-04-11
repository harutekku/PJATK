package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuPanel extends JPanel {
    public MenuPanel(int width, int height, MyFrame frame) {
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.BLACK);
        setLayout(new FlowLayout());

        JButton generateNew=new JButton("Wygeneruj nowe kształty i zapisz do pliku");
        generateNew.addActionListener(e -> {
            Main.generateFigures(20);
            try {
                FileOperations.writeBin(Main.toSave,Main.figures);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.enableDrawPanel(this);
        });

        JButton load=new JButton("Wczytaj z pliku");
        load.addActionListener(e -> {
            try {
                FileOperations.readBin(Main.toRead,Main.figures);
            } catch (IOException ex) {
                System.err.println("Nie znaleziono pliku");
            }
            frame.enableDrawPanel(this);
        });
        add(generateNew);
        add(load);
    }


}