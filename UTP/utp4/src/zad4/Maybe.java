package zad4;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T>{
	T t;

	public Maybe(T t){
		this.t=t;
	}

	public static <T> Maybe<T> of(T t){
		return new Maybe<T>(t);
	}

	public void ifPresent(Consumer consumer){
		if(this.t!=null) consumer.accept(t);
	}

	public <S> Maybe <S> map(Function<T,S> func){
		return this.t!=null?new Maybe<S>(func.apply(this.t)):new Maybe<S>(null);
	}

	public T get(){
		if(this.t!=null) return this.t;
		else throw new NoSuchElementException(" maybe is empty");
	}

	public boolean isPresent(){
		return this.t!=null;
	}

	public T orElse(T defVal){
		return isPresent()?t:defVal;
	}

	public Maybe<T> filter(Predicate<T> pred){
		return (pred.test(t)&&isPresent())?this:new Maybe(null);
	}

	public String toString() {
		return isPresent() ? "Maybe has value " + t : "Maybe is empty";
	}
}
