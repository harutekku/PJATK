package GUI1;

import java.util.ArrayList;
import java.util.List;

public abstract class Cwiczenia implements Zdawable {
    List<Integer> lista;
    public Cwiczenia(){
        lista=new ArrayList<>();
    }
    @Override
    public void dodajStudenta(int index) {
        if(lista.size()>=MAX){
            System.out.println("za duzo");
        }
        else{
            lista.add(index);
        }
    }

    abstract void sprawdzObecnosc();
}
