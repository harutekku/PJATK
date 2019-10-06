package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CountryTable {
    private ArrayList<Object[]> countries;
    private String[] columnNames;
    private Object[][] data;
    private JTable jTable;

    BufferedReader br;
    StringTokenizer st;

    public CountryTable(String countriesFileName) {
        try {
            br = new BufferedReader(new FileReader("data/countries.txt"));
            countries = new ArrayList<>();
            readColumnNames();
            readCountries();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JTable create() {
        return new JTable(data, columnNames).getColumn("Population").setCellRenderer(
                new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        if(column==3) {
                            if ((int) value > 20000) {
                                renderer.setForeground(Color.red);
                            }
                        }else {
                            Color originalColor=Color.BLACK;
                            renderer.setForeground(originalColor); // Retore original color
                        }
                        return renderer;
                    }
                }
        );
    }

    private void readColumnNames() {
        try {
            st = new StringTokenizer(br.readLine(), "\t");
            columnNames = new String[st.countTokens()];
            int i = 0;
            while (st.hasMoreTokens()) {
                columnNames[i] = st.nextToken();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readCountries() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
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
        if (population > 20000) {
            DefaultTableCellRenderer cell = new DefaultTableCellRenderer();

        }
        //Integer population=Integer.valueOf();
        /*if((Integer)ctr[3]==0){
            ctr[3]=Integer.valueOf("1");
        }*/


        /*System.out.println(ctr[0]);
        System.out.println(ctr[1]);
        System.out.println(ctr[2]);
        System.out.println(ctr[3]);*/
        countries.add(ctr);
    }

    public static void main(String[] args) {
        new CountryTable("data/countries.txt");
    }

}
