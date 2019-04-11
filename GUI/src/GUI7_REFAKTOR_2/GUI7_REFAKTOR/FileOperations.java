package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import java.io.*;
import java.util.List;

public class FileOperations {
    public static void writeBin(File file, List<Figures> list) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        for(int i=0;i<Main.figures.size();i++){
            list.get(i).write(dos);
        }
        dos.flush();
        dos.close();
    }

    public static void readBin(File file, List<Figures> list) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        boolean eof=false;
        while(!eof){
            try{
                FiguresEnum e=FiguresEnum.getEnum(in.read());
                switch (e){
                    case Oval:
                        Figures o=Oval.read(in);
                        list.add(o);
                        break;
                    case Polygon:
                        Figures p=Polygon.read(in);
                        list.add(p);
                        break;
                    case Rectangle:
                        Figures r=Rectangle.read(in);
                        list.add(r);
                        break;
                    default:
                        eof=true;
                        System.out.println("Wczytano wszystko z pliku");
                        break;
                }
            }catch (EOFException e){
                eof=true;
            }
        }
        in.close();
    }
}
