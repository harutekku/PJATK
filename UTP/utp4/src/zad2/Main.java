/**
 * @author Pawłowicz Jakub S18688
 */

package zad2;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*<-- niezbędne import */
public class Main{
	public static void main(String[] args) throws IOException{
		/*<--
		 *  definicja operacji w postaci lambda-wyrażeń:
		 *  - flines - zwraca listę wierszy z pliku tekstowego
		 *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
		 *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
		 *  - sum - zwraca sumę elmentów listy liczb całkowitych
		 */

		Wrapper<String,List<String>,IOException> flines=(fname)->{
			List<String> list=new ArrayList<>();
			BufferedReader br=new BufferedReader(new FileReader(new File(fname)));
			String line;
			while((line=br.readLine())!=null){
				list.add(line);
			}
			return list;
		};


		Function<List<String>,String> join=(lines)->{
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<lines.size();i++){
				sb.append(lines.get(i));
			}
			return sb.toString();
		};
		Function<String,List<Integer>> collectInts=(line)->{
			Pattern pattern=Pattern.compile("[0-9]+");
			Matcher matcher=pattern.matcher(line);
			List<Integer> list=new ArrayList<>();
			while(matcher.find()){
				list.add(Integer.parseInt(matcher.group()));
			}
			return list;
		};
		Function<List<Integer>,Integer> sum=(list)->{
			Integer suma=0;
			for(Integer i: list){
				suma+=i;
			}
			return suma;
		};

		String fname = System.getProperty("user.home") + "/LamComFile.txt";
		InputConverter<String> fileConv = new InputConverter<>(fname);
		List<String> lines = fileConv.convertBy(flines);
		String text = fileConv.convertBy(flines, join);
		List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
		Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

		System.out.println(lines);
		System.out.println(text);
		System.out.println(ints);
		System.out.println(sumints);

		List<String> arglist = Arrays.asList(args);
		InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
		sumints = slistConv.convertBy(join, collectInts, sum);
		System.out.println(sumints);

		// Zadania badawcze:
		// Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
		// Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
		// Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
		// I wobec tego musimy pisać w definicji flines try { } catch { }
		// Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
		// zadziałała klauzula throws metody main
	}
}
