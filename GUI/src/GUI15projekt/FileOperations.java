package GUI15projekt;

import java.io.*;
import java.util.Arrays;

public class FileOperations {
    void writeCells(ResultCellController[] cells){
        try {
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("src/GUI15projekt/results.bin")));
            dos.writeByte(cells.length);
            for (ResultCellController cell : cells) {
                dos.writeByte(cell.getNick().length());
                dos.writeChars(cell.getNick());
                dos.writeLong(cell.getTime());
                dos.writeByte(cell.getLevel());
                dos.writeBoolean(cell.getAllSwap());
            }
            dos.flush();
            dos.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ResultCellController[] readCells(){
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("src/GUI15projekt/results.bin")));
            byte length=in.readByte();
            ResultCellController[] cells=new ResultCellController[length];
            for(int i=0;i<length;i++){
                String nick="";
                for(int j=in.readByte();j>0;j--){
                    nick+=in.readChar();
                }
                cells[i]=new ResultCellController(nick,in.readLong(),in.readByte(),in.readBoolean());
            }
            Arrays.sort(cells);
            return cells;
        } catch (Exception e) {
            System.out.println("Nie znaleziono pliku");
        }
        return new ResultCellController[0];
    }

    public static void main(String[] args) { //ten main sluzy tylko do testowania operacji zapisu i odczytu
        FileOperations fo=new FileOperations();
//        ResultCellController[] cells=new ResultCellController[3];
//        cells[0]=new ResultCellController("Maciek",53342,(byte)0,false);
//        cells[1]=new ResultCellController("Stasio",236345,(byte)1,false);
//        cells[2]=new ResultCellController("Robert",812452,(byte)2,true);
//        fo.writeCells(cells);


        ResultCellController[] cells=fo.readCells();
        for(ResultCellController cell:cells){
            System.out.println(cell.getNick()+" "+cell.getTime()+" "+cell.getLevel()+" "+cell.getAllSwap());
        }
    }
}
