import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileOperations {
    public static void saveToFile(){
        try {
            DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("photo.pyra"))));
            int width=Main.dp.getWidth();
            int height=Main.dp.getHeight();
            dos.write(width);
            dos.write(height);
            BufferedImage bi=new Robot().createScreenCapture(Main.dp.getBounds());
            Graphics2D g=bi.createGraphics();
            Main.dp.paint(g);
            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    int color=bi.getRGB(j,i);
                    Color c=new Color(color);
                    System.out.println(color+" "+c.getRGB());

                    //dos.write(Main.dp);
                }
            }

        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}
