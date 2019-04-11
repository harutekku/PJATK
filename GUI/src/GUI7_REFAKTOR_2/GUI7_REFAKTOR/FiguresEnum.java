package GUI7_REFAKTOR_2.GUI7_REFAKTOR;

public enum FiguresEnum {
    Oval(0),
    Rectangle(1),
    Polygon(2),
    Error(3);
    private int value;

    FiguresEnum(int value){
        this.value=value;
    }
    public static FiguresEnum getEnum(int in) {
        if(in==0)return Oval;
        else if(in==1)return Rectangle;
        else if(in==2)return Polygon;
        else return Error;
    }

    public int getValue() {
        return value;
    }
}
