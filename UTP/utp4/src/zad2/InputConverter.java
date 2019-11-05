package zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T>{
	String fileName;
	List<String> list;

	public InputConverter(String fname){
		fileName=(fname);
	}
	public InputConverter(List<String> list){
		this.list=list;
	}

	public <T> T convertBy(Function... func){
		List<T> l=new ArrayList<>();
		l.add(((T)func[0].apply(fileName==null?list:fileName)));
		for(int i=1;i<func.length;i++){
			l.add(((T)func[i].apply(l.get(i-1))));
		}
		return l.get(l.size()-1);
	}

}
