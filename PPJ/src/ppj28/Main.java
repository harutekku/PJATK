package ppj28;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.StringTokenizer;
class Main {
    public static void main(String[] args) {
        Pattern pattern=Pattern.compile("[a-c]+h");
        Matcher matcher=pattern.matcher("aaaabbcch");
        System.out.println(matcher.find());
        matcher=pattern.matcher("bbaaaacch");
        System.out.println(matcher.find());
        matcher=pattern.matcher("ccaaacch");
        System.out.println(matcher.find());
        matcher=pattern.matcher("bbaaaabbh");
        System.out.println(matcher.find());
        matcher=pattern.matcher("abch");
        System.out.println(matcher.find());
        System.out.println();
        System.out.println();
        System.out.println();

        try{
            FileInputStream fis=new FileInputStream("src\\ppj28\\1.txt");
            int wrt;
            StringBuilder sb=new StringBuilder();
            while((wrt=fis.read())!=-1){
                sb.append((char)wrt);
            }

            fis.close();

            pattern=Pattern.compile("\\w+");
            matcher=pattern.matcher(sb);
            int co=0;
            while(matcher.find()){
                co++;
            }
            System.out.println(co);

        }catch(Exception e){
            System.out.println(e);
        }

        try{
            FileInputStream fis=new FileInputStream("src\\ppj28\\3.txt");
            int wrt;
            StringBuilder sb=new StringBuilder();
            while((wrt=fis.read())!=-1){
                sb.append((char)wrt);
            }
            fis.close();
            pattern=Pattern.compile("\\d+-\\d+");
            matcher=pattern.matcher(sb);
            int co=0;
            while(matcher.find()){
                co++;
            }
            System.out.println(co);

        }catch(Exception e){
            System.out.println(e);
        }

        try{
            FileInputStream fis=new FileInputStream("src\\ppj28\\telFormat.txt");
            int wrt;
            StringBuilder sb=new StringBuilder();
            while((wrt=fis.read())!=-1){
                sb.append((char)wrt);
            }
            fis.close();
            StringTokenizer st=new StringTokenizer(sb.toString(),"\n");

            pattern=Pattern.compile("(\\+\\d{2}\\(\\d\\d\\)\\d{7})|(\\+\\d{2}\\(\\.\\)\\d{9})");

            int co=0;
            while(st.hasMoreTokens()){
                matcher=pattern.matcher(st.nextToken());
                System.out.println(matcher.matches());
            }



        }catch(Exception e){
            System.out.println(e);
        }
    }
}