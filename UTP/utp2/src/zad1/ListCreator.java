/**
 * @author Pawłowicz Jakub S18688
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<V>{ // Uwaga: klasa musi być sparametrtyzowana
	List<V> list;

	public ListCreator(List<V> list){
		this.list=list;
	}

	public static <V> ListCreator collectFrom(List<V> list){
		return new ListCreator(list);
	}

	public <V> ListCreator when(Selector sel){
		List correct=new ArrayList<V>();
		for(int i=0;i<list.size();i++){
			if(sel.sel(list.get(i)))correct.add(list.get(i));
		}
		return new ListCreator(correct);
	}

	public List<V> mapEvery(Mapper map){
		List correct=new ArrayList<V>();
		for(int i=0;i<list.size();i++){
			correct.add(map.map(list.get(i)));
		}
		return correct;
	}
}
