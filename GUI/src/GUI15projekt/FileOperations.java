package GUI15projekt;

import java.io.*;

public class FileOperations {
    void write(){
        try {
            FileOutputStream fos=new FileOutputStream("src/GUI15projekt/results.txt");
            fos.write("maslo".getBytes());
            fos.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void read(){
        try {
            BufferedInputStream bif = new BufferedInputStream(new FileInputStream(new File("src/GUI15projekt/results.txt")));
            FileInputStream fis=new FileInputStream("src/GUI15projekt/results.txt");
            String a="";
            //for(int i=0;i<1;i++){
              //  a+= ((char) in.readByte());
            //}
            System.out.println(a+" "+fis.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileOperations fo=new FileOperations();
        fo.write();
        fo.read();
    }
}
