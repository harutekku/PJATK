/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagrams{
	List<List<String>> list;

	public Anagrams(String allWords){
		try{
			Scanner scan=new Scanner(new File(allWords));
			List<String> reader=new ArrayList<>();
			while(scan.hasNext()){
				reader.add(scan.next());
			}
			scan.close();
			this.list=new ArrayList<>();
			while(!reader.isEmpty()){
				List<String> smallList=new ArrayList<>();
				String searchFor=reader.get(0);
				smallList.add(searchFor);
				reader.remove(0);
				for(int i=reader.size()-1;i >= 0;i--){
					if(ifAnagram(searchFor,new StringBuilder(reader.get(i)))){
						smallList.add(1,reader.get(i));
						reader.remove(i);
					}
				}
				this.list.add(smallList);
			}
			this.list.sort((List<String> l1,List<String> l2)->l2.size()-l1.size());
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}


	public List<String>[] getSortedByAnQty(){
		List<String>[] array=new ArrayList[list.size()];
		for(int i=0;i<list.size();i++){
			array[i]=list.get(i);
		}
		return array;
	}

	public static boolean ifAnagram(String a,StringBuilder b){
		if(a.length()==b.length()){
			for(int i=0;i<a.length();i++){
				int j;
				for(j=0;j<b.length();j++){
					if(a.charAt(i)==b.charAt(j)){
						b.deleteCharAt(j);
						j+=b.length()+1;
					}
				}
				if(j==b.length()) return false;
			}
			if(b.length()==0) return true;
		}
		return false;
	}

	public String getAnagramsFor(String word){
		for(List<String> list: this.list){
			if(list.get(0).length()==word.length()){
				boolean here=false;
				for(String w2: list){
					if(w2.equals(word)) here=true;
				}
				if(here){
					if(list.size()>1){
						String result=word+": [";
						for(String w2: list){
							if(!w2.equals(word)) result+=w2+", ";
						}
						result=result.substring(0,result.length()-2);
						result+="]";
						return result;
					}else return word+": []";
				}
			}
		}
		return word+": null";
	}
}
