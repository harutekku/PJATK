package GUI1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dodatkowe {
    public static void main(String[] args) {
        Figura[] klocki=new Figura[4];
        klocki[0]=new Kwadrat(5);
        klocki[1]=new Prostokat(2,3);
        klocki[2]=new Kolo(5);
        klocki[3]=new Trojkat(4);
        for(Figura a:klocki){
            System.out.println(a);
        }
        System.out.println();
        System.out.println();


        List<Square> lista=new ArrayList<>();
        lista.add(new Square(3));
        lista.add(new Square(7));
        lista.add(new Square(2));
        lista.add(new Square(4));
        lista.add(new Square(5));

        for(Square a:lista){
            System.out.println(a);
        }
        Collections.sort(lista);

        System.out.println();
        System.out.println();

        for(Square a:lista){
            System.out.println(a);
        }
    }
}

abstract class Figura{
    double pole,obwod;
    String typ;
    public String toString(){
        return typ+". Pole: "+pole+" Obwod: "+obwod+"\n";
    }
}
class Kwadrat extends Figura{
    public Kwadrat(int n){
        pole=n*n;
        obwod=4*n;
        typ="Kwadrat";
    }
}
class Prostokat extends Figura{
    public Prostokat(int n, int m){
        pole=n*m;
        obwod=2*n+2*m;
        typ="Prostokat";
    }
}
class Kolo extends Figura{
    public Kolo(int r){
        pole=3.14*r*r;
        obwod=2*3.14*r;
        typ="Kolo";
    }
}
class Trojkat extends Figura{
    public Trojkat(int a){
        pole=a*a*Math.sqrt(3)/4;
        obwod=3*a;
        typ="Trojkat rownoboczny";
    }
}
class Square implements Comparable<Square>{
    int length, number;
    static int counter=0;
    public Square(int length){
        this.length=length;
        number=counter;
        counter++;
    }
    public double getArea(){
        return length*length;
    }
    public String toString(){
        return "("+number+"): "+getArea();
    }
    @Override
    public int compareTo(Square o) {
        return length-o.length;
    }
}