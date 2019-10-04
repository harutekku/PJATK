package zad1;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CountryTable {
    private ArrayList<String[]> countries;
    private String[] columnNames;
    private String[][] data;
    BufferedReader br;
    StringTokenizer st;

    public CountryTable(String countriesFileName) {
        try {
            br = new BufferedReader(new FileReader("data/countries.txt"));
            countries=new ArrayList<>();
            readColumnNames();
            readCountries();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JTable create() {
        System.out.println(columnNames[0]);
        System.out.println(columnNames[1]);
        System.out.println(columnNames[2]);
        return new JTable(data,columnNames);
    }

    private void readColumnNames() {
        try {
            st = new StringTokenizer(br.readLine(), "\t");
            columnNames = new String[st.countTokens()];
            int i=0;
            while (st.hasMoreTokens()) {
                columnNames[i]=st.nextToken();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readCountries() {
        try {
            String line=br.readLine();
            do{
                st = new StringTokenizer(line, "\t");
                readCountryData();
                line=br.readLine();
            }while (line!=null);
            data=new String[countries.size()][columnNames.length];
            for(int i=0;i<countries.size();i++){
                data[i]=countries.get(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void readCountryData() {
        String[] ctr=new String[columnNames.length];
        int i=0;
        while(st.hasMoreTokens()){
            ctr[i++]=st.nextToken();

        }
        if(i==2){
            ctr[2]=ctr[1];
            ctr[1]="";
        }
        if(ctr[2].length()>=4){
            ctr[2]=ctr[2].substring(0,ctr[2].length()-3);
        }
        else{
            ctr[2]="<1";
        }


        countries.add(ctr);
    }

    public static void main(String[] args) {
        new CountryTable("data/countries.txt");
    }

}
