package dwa;

public class SortowaniePrzezZliczanie {
    public static int[] stworzTablice(int min, int max, int n){
        int[] tab=new int[n];
        int roznica=max-min+1;
        for(int i=0;i<n;i++){
            tab[i]=(int)(Math.random()*roznica+min);
        }
        return tab;
    }
    public static void main(String[] args) {
        int[] tablica=stworzTablice(0,50,100);
        for(int a:tablica){
            System.out.print(a+" ");
        }
        System.out.println();
        int[] zliczenie=new int[51];//domyslnie w javie nowa tablica jest wypelniona zerami
        for(int i=0;i<100;i++){
            zliczenie[tablica[i]]++;
        }
        for(int a:zliczenie){
            System.out.print(a+" ");
        }
        System.out.println();
        int[] posortowana=new int[100];
        int licznik=0;
        for(int i=0;i<50;i++){
            while(zliczenie[i]>0){
                posortowana[licznik]=i;
                licznik++;
                zliczenie[i]--;
            }
        }
        for(int a:posortowana){
            System.out.print(a+" ");
        }
    }
}
