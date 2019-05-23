package GUI5;

import java.util.ArrayList;
import java.util.List;

public class Diament<T> {
    List<T> lista;
    public Diament(T t){
        lista=new ArrayList<T>();
        lista.add(t);
    }
    public void add(T t){
        lista.add(t);
    }

}
