package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<V>{
	List<V> list;

	public ListCreator(List<V> destinations){
		this.list=destinations;
	}

	public static <V> ListCreator collectFrom(List<V> destinations){
		return new ListCreator(destinations);
	}

	public <V> ListCreator when(Predicate pred){
		List correct=new ArrayList<V>();
		for(int i=0;i<list.size();i++){
			if(pred.test(list.get(i)))correct.add(list.get(i));
		}
		return new ListCreator(correct);
	}

	public List<V> mapEvery(Function func){
		List correct=new ArrayList<V>();
		for(int i=0;i<list.size();i++){
			correct.add(func.apply(list.get(i)));
		}
		return correct;
	}
}
