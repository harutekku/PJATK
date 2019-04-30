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
            bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Main.dp.paint(bi.createGraphics());
            for(int i=0;i<height;i+=1){
                for(int j=0;j<width;j+=1){
                    Color color=new Color(bi.getRGB(j,i));
                    //if(j%50==0&&i%20==0)System.out.println(j+" "+i+" "+color.getRed()+" "+color.getGreen()+" "+color.getBlue());
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
            bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            for(int i=0;i<height;i+=1){
                for(int j=0;j<width;j+=1){
                    Color color=new Color(dis.readInt());
                    //bi.setRGB(j,i,color.getRGB());
                    Main.dp.color=color;
                    g.setColor(color);
                    g.drawOval(j,i,1,1);
                    //System.out.println(color);
                }
            }
            //Main.dp.getGraphics().drawOval(10,10,10,10);

            System.out.println("done");
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}
