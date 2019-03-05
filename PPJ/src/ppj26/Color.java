package ppj26;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
public class Color {
    private int value;
    public Color(int red, int green, int blue){
        value=(red<<16)+(green<<8)+blue;
    }
    public String toString() {
        return super.toString()+" red: "+(value>>16)+ " green: "+((value>>8)&0xff)+" blue: "+(value&0xff);
    }
    public static Color generujLosowa(){
        return new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
    }
    public static String generujDane(Color a){
        return a.value/65536+ "\t"+(a.value/256)%256+"\t"+a.value%256+"\n";
    }
    public static void doPlikuString(Color[] tab) throws IOException {
        FileWriter fw=new FileWriter("src\\ppj26\\tekst.txt");
        for(int i=0;i<tab.length;i++)fw.write(generujDane(tab[i]));
        fw.flush();
        fw.close();
    }
    public static byte[] doBajtu(Color a){
        return new byte[]{((byte)(a.value/65536)),((byte)(a.value/256%256)),((byte)(a.value%256))};
    }
    public static void doPlikuBajt(Color[] tab) throws IOException {
        FileOutputStream fos=new FileOutputStream("src\\ppj26\\bajt.txt");
        for(int i=0;i<tab.length;i++){
            byte[] bajt=doBajtu(tab[i]);
            fos.write(bajt);
        }
        fos.flush();
        fos.close();
    }
    public static Color[] czytajString() throws IOException{
        FileInputStream fis=new FileInputStream("src\\ppj26\\tekst.txt");
        StringBuffer sb=new StringBuffer();
        Color[] tab=new Color[10000];
        int wrt = fis.read();
        while(wrt != -1){
            sb.append((char)wrt);
            wrt=fis.read();
        }
        StringTokenizer token=new StringTokenizer(sb.toString());
        int i=0;
        while(token.hasMoreElements()){
            tab[i]=new Color(Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()));
            i++;
        }
        fis.close();
        return tab;
    }
    public static Color[] czytajBajt() throws IOException{
        FileInputStream fis=new FileInputStream("src\\ppj26\\bajt.txt");
        Color[] tab=new Color[10000];
        int wrt=fis.read();
        int i=0;
        while(wrt != -1){
            tab[i]= new Color(wrt,fis.read(),fis.read());
            i++;
            wrt=fis.read();
        }
        fis.close();
        return tab;
    }
    public static void main(String[] args) throws IOException {
        Color a=new Color(123,222,7);
        System.out.println(a);
        Color[] tab=new Color[10000];
        for(int i=0;i<10000;i++)tab[i]=generujLosowa();
        //doPlikuString(tab);
        //doPlikuBajt(tab);
        Color[] stringowa=czytajString();
        System.out.println(stringowa[0]);
        Color[] bajtowa=czytajBajt();
        System.out.println(bajtowa[0]);
    }
}
