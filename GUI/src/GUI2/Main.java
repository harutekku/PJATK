package GUI2;

abstract class A{
    abstract int random();
}
class B extends A{
    int random() {
        return (int)(Math.random()*1000);
    }
}
class C extends A{
    private int max;
    C(int max){
        this.max=max;
    }
    int random() {
        return (int)(Math.random()*(max+1));
    }
}
interface D extends E{
    double sum(int x, double y);
}
interface E{
    double sum(double x, int y);
}
class F implements D{
    public double sum(int x, double y) {
        return sum(y,x);
    }
    public double sum(double x, int y) {
        return x+y;
    }
}


public class Main {
    public static void main(String[] args) {
        C jeden=new C(100);
        F dwa=new F();
        D trzy=new D(){
            public double sum(int x, double y) {
                return sum(y,x);
            }
            public double sum(double x, int y) {
                return x+y;
            }
        };

        System.out.println(jeden.random());
        System.out.println(dwa.sum(3,4.0));
        System.out.println(dwa.sum(2.0,3));
        System.out.println(trzy.sum(2,4.0));
        System.out.println(trzy.sum(2.0,4));


    }
}