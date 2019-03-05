package ppj29;

import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try{
            FileInputStream fis=new FileInputStream("src\\ppj29\\serverLog.txt");
            StringBuilder sb=new StringBuilder();
            int wrt;
            while((wrt=fis.read())!=-1){
                sb.append((char) wrt);
            }
            //System.out.println(sb);
            String[][] tab=new String[418][3];
            Pattern patip=Pattern.compile("(\\d{1,3}\\.){3}\\d{1,3}");
            Pattern patdt=Pattern.compile("\\d{1,2}\\D\\d{2}\\D\\d{4}");
            Pattern patms=Pattern.compile(">.*");
            Matcher matip=patip.matcher(sb);
            Matcher matdt=patdt.matcher(sb);
            Matcher matms=patms.matcher(sb);

            String[][] dane=new String[418][7];
            //Pattern dzien=Pattern.compile()

            for(int i=0;i<100;i++){
                if(matip.find()){
                    //System.out.println("ip");
                    tab[i][0]=matip.group();

                }
                if(matdt.find()){
                    //System.out.println("date");
                    tab[i][1]=matdt.group();
                }
                if(matms.find()){
                    //System.out.println("ms");
                    tab[i][2]=matms.group();
                }
            }
            //System.out.println(tab[5][0]+" "+tab[5][1]+" "+tab[5][2]);
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
