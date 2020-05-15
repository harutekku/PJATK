import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sortownik extends JFrame{
	String fromPath="F:\\Z telefonu\\memy", toPath="F:\\Fajne\\";
	final Set<String> blackList=Set.of("pdf","ini","exe","java","doc","docx","xls","xlsx","apk");
	final Set<String> whiteList=Set.of("gif","jpg","jpeg");
	boolean useWhite=false;
	//Edytowac tylko zmienne powyzej

	List<File> source;
	Map<String,String> destinations;
	int indexOfFile=-1;
	Predicate<File> filePredicate=f->{
		if(!f.isFile()) return false;
		String[] tab=f.getName().split("[.]");
		if(tab.length==1) return false;
		return useWhite?whiteList.contains(tab[tab.length-1]):!blackList.contains(tab[tab.length-1]);
	};

	public static void main(String[] args){
		SwingUtilities.invokeLater(Sortownik::new);
	}

	private Sortownik(){
		config();
		setTitle("Program redystrybujący internet");
		int sqr=(int)Math.ceil(Math.sqrt(destinations.size()+3));
		add(new JPanel(){
			{
				int rows=sqr<=4?sqr:(sqr*sqr)/4, cols=Math.min(sqr,4);
				setLayout(new GridLayout(rows,cols));
				setPreferredSize(new Dimension(cols*170,rows*80));
				JButton open=new JButton("Otwórz następny"),reset=new JButton("Reset"),back=new JButton("Cofnij");
				open.addActionListener(e->{
					try{
						if(indexOfFile>=0)kill(source.get(indexOfFile));
						Desktop.getDesktop().open(source.get(++indexOfFile));
					}catch(IOException|InterruptedException ignored){
					}
				});
				back.addActionListener(e->{
					try{
						if(indexOfFile>0){
							kill(source.get(indexOfFile));
							Desktop.getDesktop().open(source.get(--indexOfFile));
						}
					}catch(IOException|InterruptedException ignored){
					}
				});
				reset.addActionListener(e->{
					try{
						if(indexOfFile>=0)kill(source.get(indexOfFile));
						config();
						indexOfFile=-1;
					}catch(IOException|InterruptedException ignored){
					}
				});
				add(open);
				add(back);
				add(reset);
				for(Map.Entry<String,String> thisEntry: destinations.entrySet()){
					JButton button=new JButton(thisEntry.getKey());
					button.addActionListener(e->{
						try{
							kill(source.get(indexOfFile));
							moveFile(source.get(indexOfFile),thisEntry.getValue(),0);
							System.out.println("Przeniesiono "+ ++indexOfFile+" z "+source.size());
							Thread.sleep(20);
							if(indexOfFile >= source.size()){
								System.out.println("Rescan folderu");
								config();
							}
							Desktop.getDesktop().open(source.get(indexOfFile));
						}catch(IOException|InterruptedException e1){
							e1.printStackTrace();
							System.exit(0);
						}
					});
					add(button);
				}
			}
		});
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	void config(){
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

	void moveFile(File f,String url,int numberOfTry) throws InterruptedException{
		if(f.renameTo(new File(url+"\\"+f.getName()))){
			System.out.println(f.getName()+" przeniesiono do "+url);
			source.set(indexOfFile,new File(url,f.getName()));
		}
		else if(numberOfTry<5){
			TimeUnit.MILLISECONDS.sleep(100);
			moveFile(f,url,++numberOfTry);
		}
		else System.err.println("Wystapil problem z "+f.getAbsolutePath());
	}

	void kill(File f) throws IOException, InterruptedException{
		String[] tab=f.getName().split("[.]");
		switch(tab[tab.length-1]){
			case "jpg":
			case "png":
			case "jpeg":
			case "gif":
				Runtime.getRuntime().exec("taskkill /F /IM microsoft.photos.exe");
				TimeUnit.MILLISECONDS.sleep(100);
				break;
			case "mp4":
			case "avi":
			case "webm":
			case "3gp":
				Runtime.getRuntime().exec("taskkill /F /IM mpc-hc64.exe");
				TimeUnit.MILLISECONDS.sleep(200);
				break;
			default:
				System.out.println("Nie rozpoznano typu");
				break;
		}
	}
}
