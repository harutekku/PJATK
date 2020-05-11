import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class Grafical extends JFrame {
    private static String obecnyPath="F:\\Z telefonu\\JBZD";
    static File[] files=new File(obecnyPath).listFiles();
    static int indexOfFile=0;

    private Grafical() {
        setTitle("Sortownik");
        add(new buttonPanel());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    static void obrobPlik(File f, String url){
        if(f.renameTo(new File(url +"\\"+ f.getName()))){
            System.out.println(f.getName()+" przeniesiono do "+url);
        }else{
            System.out.println("Wystapil problem z plikiem!");
            System.exit(1);
        }
    }

    static void kill(File f) throws IOException {
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
            case "3gp":
                Runtime.getRuntime().exec("taskkill /F /IM mpc-hc64.exe");
                break;
            default:
                System.out.println("Nie rozpoznano typu");
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Grafical frame = new Grafical();
        });
    }

}

class buttonPanel extends JPanel{
    buttonPanel(){
        setPreferredSize(new Dimension(600,400));
        setLayout(new GridLayout(4,4));

        Map<String,String> destination=new TreeMap<>();
        destination.put("Bajtawka","F:\\Fajne\\Bajtawka");
        destination.put("Memy insajdowe","F:\\Fajne\\Memy insajdowe");
        destination.put("Memy","F:\\Fajne\\Memy ogólne");
        destination.put("Memy IT","F:\\Fajne\\Memy IT");
        destination.put("Memy reakcyjne","F:\\Fajne\\Memy reakcje");
        destination.put("Grafiki","F:\\Fajne\\Fajne grafiki");
        destination.put("Do nauki","F:\\Fajne\\Naukowe");
        destination.put("Porno","F:\\Fajne\\Porno filmy");
        destination.put("Porno zdjęcia","F:\\Fajne\\Porno fotki gify i filmiki");
        destination.put("Prywatne","F:\\Fajne\\Prywatne");
        destination.put("Różne","F:\\Fajne\\Różne rzeczy");
        destination.put("Screeny prywatne","F:\\Fajne\\Screeny ze znajomymi");
        destination.put("Do usunięcia","F:\\Fajne\\Śmieci");
        destination.put("Zwierzaki","F:\\Fajne\\Zwierzaki");
        destination.put("Feelsowe","F:\\Fajne\\Feelsowe");

        JButton open=new JButton("Otworz nastepny");
        open.addActionListener(e->{
            try {
                Desktop.getDesktop().open(Grafical.files[Grafical.indexOfFile]);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        add(open);

        JButton[] buttons=new JButton[destination.size()];
        Set<Map.Entry<String,String>> entries=destination.entrySet();
        int i=0;
        for(Map.Entry<String,String> thisEntry:entries){
            buttons[i]=new JButton(thisEntry.getKey());
            buttons[i].addActionListener(e->{
                try {
                    Grafical.kill(Grafical.files[Grafical.indexOfFile]);
                    TimeUnit.MILLISECONDS.sleep(300);
                    Grafical.obrobPlik(Grafical.files[Grafical.indexOfFile],thisEntry.getValue());
                    Grafical.indexOfFile++;
                    Desktop.getDesktop().open(Grafical.files[Grafical.indexOfFile]);
                } catch (IOException | InterruptedException e1) {
                    e1.printStackTrace();
                    System.exit(0);
                }
            });
            add(buttons[i]);
        }
    }
}
