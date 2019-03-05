/**
 * MethodCurrier
 */
public class MethodCurrier {

    public void setValue(int number) {
        System.out.println(number + " int");
        number++;
        System.out.println(number + " int");
    }
    public void setValue(float number) {
        System.out.println(number + " float");
        number++;
        System.out.println(number + " float");
    }
    public void setValue(Number a) {
        System.out.println(a.x + " int");
        a.x+=1;
        System.out.println(a.x + " int");
    }
}