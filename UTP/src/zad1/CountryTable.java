package zad1;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CountryTable {
    private List<Object[]> countries;
    private String[] columnNames;
    private Object[][] data;
    private JTable jTable;
    private BufferedReader br;
    private StringTokenizer st;

    CountryTable(String countriesFileName) throws IOException {
        br = new BufferedReader(new FileReader(countriesFileName));
        countries = new ArrayList<>();
        readColumnNames();
        readCountries();
    }

    private void readColumnNames() throws IOException {
        st = new StringTokenizer(br.readLine(), "\t");
        columnNames = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            columnNames[i] = st.nextToken();
            i++;
        }
    }

    private void readCountries() throws IOException {
        String line = br.readLine();
        do {
            st = new StringTokenizer(line, "\t");
            readCountryData();
            line = br.readLine();
        } while (line != null);
        data = new Object[countries.size()][columnNames.length];
        for (int i = 0; i < countries.size(); i++) {
            data[i] = countries.get(i);
        }
    }

    private void readCountryData() {
        Object[] ctr = new Object[columnNames.length];
        ctr[0] = st.nextToken();
        ctr[1] = st.nextToken();
        ctr[2] = st.nextToken();
        int population = Integer.valueOf(st.nextToken());
        population /= 1000;
        if (population == 0) population = 1;
        ctr[3] = population;
        countries.add(ctr);
    }

    JTable create() {
        jTable = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setRowHeight(35);
        jTable.getColumn("Flag").setMaxWidth(50);
        jTable.getColumn("Flag").setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                File file = new File("data/flag_icons/" + value + ".png");
                BufferedImage image;

                try {
                    image = ImageIO.read(file);
                } catch (IOException e) {
                    return new JLabel();
                }
                return new JLabel(new ImageIcon(image.getScaledInstance(40, 28, 1)));
            }
        });
        jTable.getColumn("Population(k)").setCellRenderer(
                new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        renderer.setHorizontalAlignment(JLabel.RIGHT);
                        if ((int) value > 20000) {
                            renderer.setForeground(Color.red);
                        } else {
                            renderer.setForeground(Color.BLACK);
                        }
                        return renderer;
                    }
                }
        );
        return jTable;
    }
}
