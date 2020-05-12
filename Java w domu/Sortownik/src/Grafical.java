import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Grafical extends JFrame{
    static List<File> source;
    static Map<String,String> destinations;
    static int indexOfFile=0;

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            Grafical frame=new Grafical();
        });
    }
    private Grafical(){
        String obecnyPath="F:\\Z telefonu\\JBZD";
        String docelowyPath="F:\\Fajne\\";
        config(obecnyPath,docelowyPath);
        setTitle("Sortownik");
        add(new buttonPanel(source,destinations));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    static void config(String from, String to){
        ArrayList<File> fromDirs=new ArrayList<>(Arrays.asList(Objects.requireNonNull(new File(from).listFiles((x)->!x.isFile()))));
        for(int i=0;i<fromDirs.size();i++)
            if(!fromDirs.get(i).isFile())
                fromDirs.addAll(Arrays.asList(Objects.requireNonNull(fromDirs.get(i).listFiles((x)->!x.isFile()))));
        source=new ArrayList<>();
        source.addAll(Arrays.asList(Objects.requireNonNull(new File(from).listFiles(File::isFile))));
        for(File dir: fromDirs){
            source.addAll(Arrays.asList(Objects.requireNonNull(dir.listFiles(File::isFile))));
        }

        ArrayList<File> toDirs=new ArrayList<>(Arrays.asList(Objects.requireNonNull(new File(to).listFiles((x)->!x.isFile()))));
        for(int i=0;i<toDirs.size();i++)
            if(!toDirs.get(i).isFile())
                toDirs.addAll(Arrays.asList(Objects.requireNonNull(toDirs.get(i).listFiles((x)->!x.isFile()))));
        destinations=new TreeMap<>();
        for(File f: toDirs) destinations.put(f.getName(),f.getAbsolutePath());
    }

	static void obrobPlik(File f,String url){
		if(f.renameTo(new File(url+"\\"+f.getName()))){
			System.out.println(f.getName()+" przeniesiono do "+url);
		}else{
			System.out.println("Wystapil problem z plikiem!");
			System.exit(1);
		}
	}

	static void kill(File f) throws IOException{
		String typ="";
		int i=f.getName().lastIndexOf('.');
		if(i>0) typ=f.getName().substring(i+1);

		switch(typ){
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
				break;
		}
	}

}
class buttonPanel extends JPanel{

	buttonPanel(List<File> sources, Map<String,String> destinations){
		int sqr=(int)Math.ceil(Math.sqrt(destinations.size()+1));
		setLayout(new GridLayout(sqr,Math.min(sqr,4)));
		setPreferredSize(new Dimension(sqr*140,sqr*120));
        JButton open=new JButton("Otworz nastepny");
        open.addActionListener(e->{
            try{
                Desktop.getDesktop().open(sources.get(Grafical.indexOfFile));
            }catch(IOException e1){
                e1.printStackTrace();
            }
        });
        add(open);
		JButton[] buttons=new JButton[destinations.size()];
		Set<Map.Entry<String,String>> entries=destinations.entrySet();
		int i=0;
		for(Map.Entry<String,String> thisEntry: entries){
			buttons[i]=new JButton(thisEntry.getKey());
			buttons[i].addActionListener(e->{
				try{
					Grafical.kill(sources.get(Grafical.indexOfFile));
					TimeUnit.MILLISECONDS.sleep(300);
					Grafical.obrobPlik(sources.get(Grafical.indexOfFile),thisEntry.getValue());
                    Grafical.indexOfFile++;
					if(Grafical.indexOfFile >= sources.size()){
						System.out.println("Koniec danego folderu");
						System.exit(0);
					}
					Desktop.getDesktop().open(sources.get(Grafical.indexOfFile));
                    System.err.println("Przeniesiono "+Grafical.indexOfFile+" z "+sources.size());
				}catch(IOException|InterruptedException e1){
					e1.printStackTrace();
					System.exit(0);
				}
			});
			add(buttons[i]);
		}
	}
}
