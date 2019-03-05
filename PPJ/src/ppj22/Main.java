package ppj22;

import java.io.FileNotFoundException;

public class Main {
    static void podniesArrayIndexOutOfBoundsException() throws ArrayIndexOutOfBoundsException {
        throw new ArrayIndexOutOfBoundsException();
    }

    static void podniesFileNotFoundException() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    static void podniesException() throws Exception {
        throw new Exception();
    }

    public static int podajLiczbe(String a){
        int dziesietna=0;
        try {
            dziesietna=Integer.parseInt(a, 2);
        } catch(NumberFormatException b){
            try {
                dziesietna=Integer.parseInt(a, 8);
            } catch(NumberFormatException c){
                try {
                    dziesietna=Integer.parseInt(a, 16);
                } catch(NumberFormatException d){
                    System.out.println(d);
                }
            }
        }

        return dziesietna;
    }

    public static void main(String[] args) {

        try {
            podniesArrayIndexOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            podniesFileNotFoundException();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        try {
            podniesException();
        } catch (Exception e) {
            System.out.println("Zla jednostka");

        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(podajLiczbe("11"));
        System.out.println();
        System.out.println(podajLiczbe("12"));
        System.out.println();
        System.out.println(podajLiczbe("18"));
        System.out.println();
        System.out.println(podajLiczbe("G"));
    }
}
