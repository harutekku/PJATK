package GUI6;

public class Rekurencja {

    static int array_sum(int[] tab, int i){
        if(i<tab.length){
            return tab[i]+array_sum(tab,++i);
        }
        else{
            return 0;
        }
    }


    public static void main(String[] args) {
        int tab[]={1,2,3,4,5};
        System.out.println(array_sum(tab,0));
    }
}
