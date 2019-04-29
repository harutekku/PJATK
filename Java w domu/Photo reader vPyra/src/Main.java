import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            DataInputStream dis=new DataInputStream(new BufferedInputStream(new FileInputStream(new File("kot.bmp"))));
            char[] header=new char[2];
            for(int i=0;i<2;i++){
                header[i]= (char) dis.readByte();
            }
            System.out.println( header[0]+" "+header[1] );
            int size=dis.readUnsignedByte()+(dis.readUnsignedByte()<<8)+(dis.readUnsignedByte()<<16)+(dis.readUnsignedByte()<<24);
            System.out.println(size);
            long dupsko=dis.readLong();
            dupsko=dis.readLong();

            byte[] tab = new byte[14];
            for(int i=0;i<14;i++){
                tab[i]=dis.readByte();
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
