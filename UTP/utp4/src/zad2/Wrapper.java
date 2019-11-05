package zad2;


import java.io.IOException;
import java.util.function.Function;

public interface Wrapper <T,R,Z extends Exception> extends Function<T,R>{
	R maslo(T t) throws Z;

	@Override
	default R apply(T t){
		try{
			return maslo(t);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
