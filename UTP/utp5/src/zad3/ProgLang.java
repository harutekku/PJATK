package zad3;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ProgLang{
	List<String> list;

	public ProgLang(String fname) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader(new File(fname)));
		list=new ArrayList<>();
		String line;
		while((line=br.readLine())!=null){
			list.add(line);
		}
	}

	public Map<String, List<String>> getLangsMap(){
		Map<String,List<String>> map=new LinkedHashMap<>();
		Scanner scan;
		for(String line:list){
			scan=new Scanner(line).useDelimiter("\t");
			String lang=scan.next();
			List<String> names=new ArrayList<>();
			while(scan.hasNext()){
				String name=scan.next();
				if(!names.contains(name))names.add(name);
			}
			scan.close();
			map.put(lang,names);
		}
		return map;
	}

	public Map<String, List<String>> getProgsMap(){
		Map<String,List<String>> map=new LinkedHashMap<>();
		Scanner scan;
		for(String line:list){
			scan=new Scanner(line).useDelimiter("\t");
			String lang=scan.next();
			while(scan.hasNext()){
				String programmer=scan.next();
				if(!map.containsKey(programmer)){
					List<String> langs=new ArrayList<>();
					langs.add(lang);
					map.put(programmer,langs);
				}
				else{
					List langs=map.get(programmer);
					if(!langs.contains(lang)){
						langs.add(lang);
					}
				}

			}
			scan.close();
		}
		return map;
	}

	public Map<String, List<String>> getLangsMapSortedByNumOfProgs(){
		Map<String,List<String>> map=getLangsMap();
		Map<String, List<String>> result = map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.compa))
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return map;
	}

	public Iterable<Object> getProgsMapSortedByNumOfLangs(){
		return null;
	}

	public Iterable<Object> getProgsMapForNumOfLangsGreaterThan(int i){
		return null;
	}
}
