import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileOperations {
    public static void saveToFile(){
        try {
            DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("photo.pyra"))));
            int width=Main.dp.getWidth();
            int height=Main.dp.getHeight();
            System.out.println(width+" "+height);
            dos.writeInt(width);
            dos.writeInt(height);
            Robot ro=new Robot();
            BufferedImage bi=ro.createScreenCapture(Main.dp.getBounds());
            for(int i=0;i<height;i+=1){
                for(int j=0;j<width;j+=1){
                    Color color=new Color(bi.getRGB(j,i));
                    //System.out.println(j+" "+i+" "+color.getRed()+" "+color.getGreen()+" "+color.getBlue());
                    dos.writeInt(color.getRGB());
                }
            }
            System.out.println("done");
            dos.flush();
            dos.close();

        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }
    static void readFromFile(){
        try{
            DataInputStream dis=new DataInputStream((new BufferedInputStream(new FileInputStream(new File("photo.pyra")))));
            int width=dis.readInt();
            int height=dis.readInt();
            System.out.println(width+" "+height);
            Graphics2D g= (Graphics2D) Main.dp.getGraphics();
            Robot ro=new Robot();
            BufferedImage bi=ro.createScreenCapture(Main.dp.getBounds());
            for(int i=0;i<height;i+=10){
                for(int j=0;j<width;j+=50){
                    Color color=new Color(dis.readInt());
                    bi.setRGB(j,i,color.getRGB());
                    System.out.println(color);
                }
            }
            System.out.println("done");
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}
