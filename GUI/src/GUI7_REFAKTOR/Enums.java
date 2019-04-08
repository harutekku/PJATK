package GUI7_REFAKTOR;

public enum Enums {
    Owal(0),
    Poly(1),
    Rectangle(2);
    private int value;
    Enums(int value){
        this.value=value;
    }
    public static Enums getEnum(int in) {
        if(in==0)return Owal;
        else if(in==1)return Poly;
        else return Rectangle;
    }

    public int getValue() {
        return value;
    }
}
