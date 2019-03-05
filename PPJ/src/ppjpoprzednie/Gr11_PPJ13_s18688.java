public class Gr11_PPJ13_s18688 {
    public static void show(int a){
        System.out.print(a+" ");
    }
    public static void show(int[] a){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static void show(char[] a){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
        }
    }
    public static void modifyValue(int wrt){
        show(wrt);
        wrt*=5;
        show(wrt);
    }
    public static int[][] resizeArray(int[][] tab){
        int[][] newArray=new int[tab.length+1][2];
        for(int i=0;i<tab.length;i++){
            newArray[i][0]=tab[i][0];
            newArray[i][1]=tab[i][1];
        }
        return newArray;
    }
    public static void charCounter(char[] tab){
        int[][] counter=new int[0][2];
        for(int i=0;i<tab.length;i++){
            boolean ifCount=false;
            for(int j=0;j<counter.length;j++){
                if(tab[i]==counter[j][0]){
                    ifCount=true;
                    counter[j][1]++;
                }
            }
            if(!ifCount){
                counter=resizeArray(counter);
                counter[counter.length-1][0]=tab[i];
                counter[counter.length-1][1]++;
            }
        }
        for(int i=0;i<counter.length;i++){
            System.out.println("Znakow: '"+(char)counter[i][0]+"' jest "+counter[i][1]);
        }
    }
    public static int[] randomize(int[] tab, int a){
        for(int i=0;i<tab.length;i++){
            tab[i]=(int)(Math.random()*a);
        }
        return tab;
    }
    public static int[][] randomize(int[][] tab, int a){
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[i].length;j++){
                tab[i][j]=(int)(Math.random()*a);
            }
        }
        return tab;
    }
    public static int[] twoArraysOneInt(int[] tab1, int[] tab2, int a){
        if(tab1.length==tab2.length){
            int[] tab={};
            return tab;
        }
        if(a>0){
            if(tab1.length>tab2.length){
                int[] tab=new int[tab1.length-tab2.length];
                for(int i=tab2.length;i<tab1.length;i++){
                    tab[i-tab2.length]=tab1[i];
                }
                return tab;
            }
            else{
                int[] tab=new int[tab2.length-tab1.length];
                for(int i=tab1.length;i<tab2.length;i++){
                    tab[i-tab1.length]=tab2[i];
                }
                return tab;
            }
        }
        if(tab1.length>tab2.length){
            int[] tab=new int[tab2.length];
            for(int i=0;i<tab.length;i++){
                tab[i]=tab1[i]+tab2[i];
            }
            return tab;
        }
        else{
            int[] tab=new int[tab1.length];
            for(int i=0;i<tab.length;i++){
                tab[i]=tab1[i]+tab2[i];
            }
            return tab;
        }
    }
    public static boolean ifPalindrom(char[] word){
        if(word.length==1){
            return true;
        }
        else if(word.length==2){
            if(word[0]==word[1]){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            char[] newWord=new char[word.length-2];
            for(int i=0;i<newWord.length;i++){
                newWord[i]=word[i+1];
            }
            if(ifPalindrom(newWord) && word[0]==word[word.length-1]){
                return true;
            }
            else return false;
        }
    }
    public static void main(String[] args){
        /* Zadanie 1 ----------------------------------------------------------- */ System.out.println();System.out.println();System.out.println();
        show(5);

        /* Zadanie 2 ----------------------------------------------------------- */ System.out.println();System.out.println();System.out.println();
        int wrt=5;
        show(wrt);
        System.out.println();
        modifyValue(wrt);
        System.out.println();
        show(wrt);
        //Dzieje się tak ponieważ kiedy wywołujemy metodę, ona nie bierze naszej zmiennej, tylko ją kopiuje i dopóki jesteśmy w metodzie to pracujemy tylko na kopii

        /* Zadanie 3 ----------------------------------------------------------- */ System.out.println();System.out.println();System.out.println();
        char[] ala="Ala ma kota".toCharArray();
        charCounter(ala);

        /* Zadanie 4 ----------------------------------------------------------- */ System.out.println();System.out.println();System.out.println();
        int[] taba=new int[10],tabb=new int[15];
        int intt=1;

        randomize(taba,100); randomize(tabb,100);
        show(taba);System.out.println();show(tabb);
        System.out.println();
        int[] tabc=twoArraysOneInt(taba, tabb, intt);
        show(tabc);

        /* Zadanie 5 ----------------------------------------------------------- */ System.out.println();System.out.println();System.out.println();
        char[] palindrom="kajak".toCharArray();
        show(palindrom);
        if(ifPalindrom(palindrom)){
            System.out.print(" to palindrom");
        }
        else{
            System.out.print(" nie jest palindromem");
        }

        /* Zadanie 6 ----------------------------------------------------------- */ System.out.println();System.out.println();System.out.println();
        int[][] tabd=new int[25][25];
        randomize(tabd, 2);
        for(int i=0;i<tabd.length;i++){
            show(tabd[i]);
        }

        /* Zadanie 7 ----------------------------------------------------------- */ System.out.println();System.out.println();System.out.println();
    }
    
}