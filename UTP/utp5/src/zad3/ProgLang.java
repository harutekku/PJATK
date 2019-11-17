package zad3;


import java.io.*;
import java.util.*;
import java.util.function.Predicate;

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

	public static <T, K> Map<T,K> sorted(Map<T,K> map, Comparator<Map.Entry<T,K>> comparator) {
		LinkedHashMap<T,K> sortedMap = new LinkedHashMap<>();
		map.entrySet().stream()
				.sorted(comparator)
				.forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

		return sortedMap;
	}

	public static <T, K> Map<T, K> filtered(Map<T, K> map, Predicate<Map.Entry<T, K>> predicate) {
		LinkedHashMap<T, K> filteredMap = new LinkedHashMap<>();
		map.entrySet().stream()
				.filter(predicate)
				.forEach(entry -> filteredMap.put(entry.getKey(), entry.getValue()));

		return filteredMap;
	}

	public Map<String, List<String>> getLangsMapSortedByNumOfProgs(){
		return sorted(getLangsMap(),(l1, l2) ->{
			int result=Integer.compare(l2.getValue().size(),l1.getValue().size());
			return result!=0?result:l1.getKey().compareTo(l2.getKey());
		});
	}

	public Map<String, List<String>> getProgsMapSortedByNumOfLangs(){
		return sorted(getProgsMap(),(p1, p2) ->{
			int result=Integer.compare(p2.getValue().size(),p1.getValue().size());
			return result!=0?result:p1.getKey().compareTo(p2.getKey());
		});
	}

	public Map<String,List<String>> getProgsMapForNumOfLangsGreaterThan(int i){
		return filtered(getProgsMap(),(p1)->p1.getValue().size()>i);
	}
}
