package ppj21;

import ppj20.Drzewo;

public class Main {
    public static void sprawdz(int[][] tab)throws Alarm{
        String bledy="Tablica nie spelnia wymagan. Bledy na pozycjach: ";
        boolean czyBlad=false;
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab.length;j++){
                if(i!=j){
                    if(tab[i][j]!=0){
                        czyBlad=true;
                        bledy+="["+i+", "+j+"], ";
                    }
                }
            }
        }
        if(czyBlad){
            throw new Alarm(bledy);
        }
    }
    public static void pokaz(int[][] tab){
        for(int i=0;i<tab.length;i++) {
            for(int j=0;j<tab[i].length;j++) {
                System.out.print(tab[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void wypelnij(int[][] tab){
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[i].length;j++){
                tab[i][j]=(int)(Math.random()*6);
            }
        }
    }
    public static void main(String[] args) {
        DetektorDymu detek = new DetektorDymu(true);

        try {
            detek.sprawdz();
        } catch (Alarm a) {
            System.out.println(a.getMessage());
        }

        int[][] tab=new int[3][3];
        wypelnij(tab);
        pokaz(tab);
        try {
            sprawdz(tab);
        }catch(Alarm alarm) {
            System.out.println(alarm.getMessage());
        }

        Drzewo sosny=new Drzewo(true,10,"Ladny");
        Drzewo modrzewie=new Drzewo(true,10,"Ladny");
        Drzewo dęby=new Drzewo(false,20,"Ladny");
        Drzewo osiki=new Drzewo(false,20,"Ladny");
        Drzewo morelowce=new Drzewo(false,30,"Ladny");
        Drzewo śliwy=new Drzewo(false,30,"Ladny");
        Drzewo[] las={sosny, modrzewie, dęby, osiki, morelowce, śliwy};

        for(int i=0;i<las.length;i++){
            System.out.println(las[i].toString());
        }
    }
}
