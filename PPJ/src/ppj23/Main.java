package ppj23;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args){
        InputStream strumienWe=System.in;

//        try{
//            int line=strumienWe.read();
//            System.out.println((char)(line));
//        }catch (IOException a){
//            System.out.println(a);
//        }

        try{
            int znak=0;
            while((znak=strumienWe.read())!=-1){
                System.out.println("Wartosc char - " + znak);
            }
        }catch(IOException a){
            System.out.println(a);
        }


    }
}
