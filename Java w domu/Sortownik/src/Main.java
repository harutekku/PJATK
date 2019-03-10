import java.awt.*;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Main {
    public static void zabij(File f)throws Exception{
        String typ="";
        int i = f.getName().lastIndexOf('.');
        if (i > 0) {
            typ=f.getName().substring(i+1);
        }
        switch (typ){
            case "jpg":
            case "png":
            case "jpeg":
            case "gif":
                Runtime.getRuntime().exec("taskkill /F /IM microsoft.photos.exe");
                break;
            case "mp4":
            case "avi":
            case "webm":
                Runtime.getRuntime().exec("taskkill /F /IM mpc-hc64.exe");
                break;
            default:
                System.out.println("Nie rozpoznano typu");
                System.exit(0);
                break;
        }
    }
    public static void przenies(File f)throws Exception{
        Scanner skan=new Scanner(System.in);
        String dokad="F:\\Fajne";
        String anime="F:\\Fajne\\Anime";
        String chore="F:\\Fajne\\Chore";
        String ciekawe="F:\\Fajne\\Ciekawe";
        String kotki="F:\\Fajne\\Kotki pieski";
        String laski="F:\\Fajne\\Laski";
        String memy="F:\\Fajne\\Memy";
        String prywatne="F:\\Fajne\\Moje i znajomi";
        String porno="F:\\Fajne\\Porno";
        String tapety="F:\\Fajne\\Tapety";
        String reakcje="F:\\Fajne\\Reakcje memowe";
        String rozne="F:\\Fajne";

        System.out.println("Q: zakoncz, W: memy, E: laski, R: anime, T: chore, A: ciekawe, S: kotki, D: prywatne, F: porno, Z: tapety, X: rozne, V: reakcje, C: usun");
        String gdzie=skan.next();
        zabij(f);
        TimeUnit.MILLISECONDS.sleep(300);
        switch(gdzie){
            case "W":case "w":
                dokad=memy;
                break;
            case "E":case "e":
                dokad=laski;
                break;
            case "R":case "r":
                dokad=anime;
                break;
            case "T":case "t":
                dokad=chore;
                break;
            case "A":case "a":
                dokad=ciekawe;
                break;
            case "S":case "s":
                dokad=kotki;
                break;
            case "D":case "d":
                dokad=prywatne;
                break;
            case "F":case "f":
                dokad=porno;
                break;
            case "Z":case "z":
                dokad=tapety;
                break;
            case "X":case "x":
                dokad=rozne;
                break;
            case "C":case "c":
                f.delete();
                return;
            case "V":case "v":
                dokad=reakcje;
                break;
            case "Q":case "q":default:
                System.exit(0);
                break;
        }
        if(f.renameTo(new File(dokad +"\\"+ f.getName()))){
            System.out.println(f.getName()+" przeniesiono do "+dokad);
        }else{
            System.out.println("Wystapil problem z plikiem!");
            System.exit(0);
        }
    }
    public static void obrobPlik(File f)throws Exception{
        Desktop.getDesktop().open(f);
        przenies(f);
        System.out.println();
    }
    public static void main(String[] args) {
        String obecnyPath="F:\\Z telefonu\\Facebook";
        File[] files=new File(obecnyPath).listFiles();
        try {
            for(File f:files){
                if(f.isFile())obrobPlik(f);
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
