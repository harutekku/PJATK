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
        return new Maybe<>(t);
    }

    public void ifPresent(Consumer consumer){
        if(this.t!=null) consumer.accept(t);
    }

    public Maybe<T> map(Function func){
        return this.t!=null?new Maybe(func.apply(this.t)):new Maybe(null);
    }

    public T get(){
        if(this.t!=null) return this.t;
        else throw new NoSuchElementException();
    }

    public boolean isPresent(){
        return this.t!=null;
    }

    public T orElse(T defVal){
        return isPresent()?t:defVal;
    }

    public<T> Maybe<T> filter(Predicate<T> pred){
        return (pred.test((T)t) && isPresent())?new Maybe(t):new Maybe(null);
    }
}
