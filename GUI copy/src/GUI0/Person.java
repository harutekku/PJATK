package GUI0;

public class Person {
    public String name;
    public int age;

    public Person(String na, int a){
        name=na;
        age=a;
    }

    public void sayHelloTo(Person osoba){
        System.out.println("Hi "+osoba.name+"!");
    }
}
