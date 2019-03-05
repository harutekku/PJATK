package jeden;

class B {
    String name;
    B(String s) {
        super();
        name = "" + s + "\n";
    }
}

class A extends B {
    static int foo(B obj, int x) {
        if(obj == null) return 0;
        if(x <= 0) return -1;
        System.out.print(obj);
        System.out.print(obj.name);
        return x + foo(obj, x - 2);
    }

    public String toString() {
        return "" + name + "\n";
    }

    A(String s) {
        super("#" + s);
        name += "#";
    }

    public boolean equals(Object o) {
        return name.charAt(1) == ((B) o).name.charAt(0);
    }
}

class C extends B {
    C(String s) {
        super("s");
    }

    C(String s, int x) {
        super(s);
        System.out.println(super.toString());
    }

    public String toString() {
        return super.toString() + "\n\t#";
    }
}

public class jeden {
    public static void main(String[] args) {
        B b = new A("x");
        A a = new A("x");
        B b2 = b;
        B b3 = new B("x");

        System.out.println(b.name);
        System.out.println(a.name);
        System.out.println(b2.name);
        System.out.println();

        System.out.println(b.equals(a));
        System.out.println(b.equals(b2));
        System.out.println(b.equals(b3));
        System.out.println();

        System.out.println(A.foo(b2, 8));

        System.out.println();
        B[] bs = new B[5];

        bs[0] = b.equals(b2) ? b : null;
        bs[1] = a;
        bs[3] = new C("y");
        bs[3] = a.toString().equals("#x#") ? a : b;
        bs[4] = b3;
        for(int i = 0; i < bs.length; i++) {
            System.out.println(i+" "+bs[i]);
        }
    }
}