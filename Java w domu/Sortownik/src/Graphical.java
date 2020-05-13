import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Graphical extends JFrame{
	static String fromPath="F:\\Z telefonu\\";
	static String toPath="F:\\Fajne\\";
	static final Set<String> skippedExtensions=Set.of(
			"pdf","ini","exe","java","webm","doc","docx","xls","xlsx","mp4","apk"
	);

	static List<File> source;
	static Map<String,String> destinations;
	static int indexOfFile=0;
	static Predicate<File> filePredicate=f->{
		if(!f.isFile()) return false;
		String[] tab=f.getName().split("[.]");
		if(tab.length==1) return false;
		return !skippedExtensions.contains(tab[tab.length-1]);
	};

	public static void main(String[] args){
		SwingUtilities.invokeLater(Graphical::new);
	}

	private Graphical(){
		config();
		setTitle("Program redystrybujący internet");
		add(new buttonPanel());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	static void config(){
		try{
			File from=new File(fromPath);
			ArrayList<File> fromDirs=new ArrayList<>(Arrays.asList(Objects.requireNonNull(from.listFiles((x)->!x.isFile()))));
			for(int i=0;i<fromDirs.size();i++)
				if(!fromDirs.get(i).isFile())
					fromDirs.addAll(Arrays.asList(Objects.requireNonNull(fromDirs.get(i).listFiles((x)->!x.isFile()))));
			source=Arrays.stream(Objects.requireNonNull(new File(fromPath).listFiles())).filter(filePredicate).collect(Collectors.toList());
			for(File dir: fromDirs){
				source.addAll(Arrays.stream(Objects.requireNonNull(dir.listFiles())).filter(filePredicate).collect(Collectors.toList()));
			}
		}catch(NullPointerException e){
			System.err.println("Sciezka nie istnieje");
			System.exit(0);
		}
		System.out.println("Znaleziono "+source.size()+" plikow");

		if(indexOfFile >= source.size()){
			System.err.println("Folder jest pusty");
			System.exit(0);
		}

		ArrayList<File> toDirs=new ArrayList<>(Arrays.asList(Objects.requireNonNull(new File(toPath).listFiles((x)->!x.isFile()))));
		for(int i=0;i<toDirs.size();i++)
			if(!toDirs.get(i).isFile())
				toDirs.addAll(Arrays.asList(Objects.requireNonNull(toDirs.get(i).listFiles((x)->!x.isFile()))));
		destinations=new TreeMap<>();
		for(File f: toDirs) destinations.put(f.getName(),f.getAbsolutePath());
	}

	static void moveFile(File f,String url){
		if(f.renameTo(new File(url+"\\"+f.getName()))){
			System.out.println(f.getName()+" przeniesiono do "+url);
		}else{
			System.out.println("Wystapil problem z plikiem!");
		}
	}

	static void kill(File f) throws IOException{
		String typ="";
		int i=f.getName().lastIndexOf('.');
		if(i>0) typ=f.getName().substring(i+1);
		switch(typ.toLowerCase()){
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

	buttonPanel(){
		int sqr=(int)Math.ceil(Math.sqrt(Graphical.destinations.size()+1));
		//if(sqr<=4) setLayout(new GridLayout(sqr,sqr));
		//else setLayout(new GridLayout((Graphical.destinations.size()+1)/4+1,4));
		setLayout(sqr<=4?new GridLayout(sqr,sqr):new GridLayout((sqr*sqr)/4,4));
		setPreferredSize(new Dimension(sqr*140,sqr*120));
		JButton open=new JButton("Otworz nastepny");
		open.addActionListener(e->{
			try{
				Desktop.getDesktop().open(Graphical.source.get(Graphical.indexOfFile));
			}catch(IOException e1){
				e1.printStackTrace();
			}
		});
		add(open);
		JButton[] buttons=new JButton[Graphical.destinations.size()];
		Set<Map.Entry<String,String>> entries=Graphical.destinations.entrySet();
		int i=0;
		for(Map.Entry<String,String> thisEntry: entries){
			buttons[i]=new JButton(thisEntry.getKey());
			buttons[i].addActionListener(e->{
				try{
					Graphical.kill(Graphical.source.get(Graphical.indexOfFile));
					TimeUnit.MILLISECONDS.sleep(300);
					Graphical.moveFile(Graphical.source.get(Graphical.indexOfFile),thisEntry.getValue());
					System.err.println("Przeniesiono "+ ++Graphical.indexOfFile+" z "+Graphical.source.size());
					Thread.sleep(20);
					if(Graphical.indexOfFile >= Graphical.source.size()){
						System.out.println("Rescan folderu");
						Graphical.config();
					}
					Desktop.getDesktop().open(Graphical.source.get(Graphical.indexOfFile));
				}catch(IOException|InterruptedException e1){
					e1.printStackTrace();
					System.exit(0);
				}
			});
			add(buttons[i]);
		}
	}
}
