package zad1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T>{
	List<String> list;
	public InputConverter(String address){
		try{
			BufferedReader br=new BufferedReader(new FileReader(new File(address)));
			list=new ArrayList<String>();
			String line;
			while((line=br.readLine())!=null){
				list.add(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public <S>S convertBy(Function... func){
		return null;
	}
}
