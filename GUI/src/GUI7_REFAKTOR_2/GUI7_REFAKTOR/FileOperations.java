package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

import java.io.*;
import java.util.List;

class FileOperations {
    static void clearFile(File file){
        try {
            FileOutputStream fos=new FileOutputStream(file);
            fos.close();
            System.out.println("Wyczyszczono plik");
        } catch (IOException e) {
            System.err.println("Nie znaleziono pliku");
        }
    }
    static void writeAllBin(File file, List<Figures> list) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        for(int i=0;i<Main.figures.size();i++){
            list.get(i).write(dos);
        }
        dos.flush();
        dos.close();
    }
    static void writeOneObjectBin(File file, Figures figure) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file,true)));
        figure.write(dos);
        dos.flush();
        dos.close();
    }

    static void readBin(File file, List<Figures> list) throws IOException {
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
