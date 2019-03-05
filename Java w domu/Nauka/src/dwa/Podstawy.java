package dwa;

public class Podstawy {

    public static void main(String[] args){

        for(int i=0;i<10;i++){
            /*do something*/
        }

        for(int i=0;i<10;i++) /*do something*/ ;


        if(true){
            /*do something*/
        }
        else if(true){
            /*do something*/
        }
        else{
            /*do something*/
        }

        if(true)/*do something*/;

        else if(true)/*do something*/;

        else /*do something*/ ;


        // for(tworzenie zmiennych; czesc warunkowa; inkrementacja zmiennych)

        boolean czy=true;

        for(int i=0;i<10 && czy;i++){
            System.out.print(i+" ");
        }
        System.out.println("\n\n ");
        czy=false;
        for(int i=0;i<10 || czy;i++){
            System.out.print(i+" ");
        }

    }
}
