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
        String memy="F:\\Z telefonu dobre\\Memy";
        String laski="F:\\Z telefonu dobre\\laski";
        String prywatne="F:\\Z telefonu dobre\\Prywatne";
        String pieski="F:\\Z telefonu dobre\\Kotki piski i inne";
        String chore="F:\\Z telefonu dobre\\Anime i chore";
        String rozne="F:\\Z telefonu dobre\\Różne rzeczy";
        String dokad="";
        System.out.println("1: memy, 2: laski, 3: prywatne, 4: pieski, 5: chore, 6: rozne 7: usun 0: zakoncz");
        int gdzie=skan.nextInt();
        zabij(f);
        TimeUnit.MILLISECONDS.sleep(300);
        switch(gdzie){
            case 1:
                dokad=memy;
                break;
            case 2:
                dokad=laski;
                break;
            case 3:
                dokad=prywatne;
                break;
            case 4:
                dokad=pieski;
                break;
            case 5:
                dokad=chore;
                break;
            case 6:
                dokad=rozne;
                break;
            case 7:
                f.delete();
                return;
            case 0:default:
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
        String obecnyPath="F:\\Z telefonu\\memy";
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
