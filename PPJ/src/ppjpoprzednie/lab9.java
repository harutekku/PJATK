public class lab9{
    public static void main(String[] args){
        int[] tablica1=new int[20];
        for(int i=0;i<tablica1.length;i++){
            tablica1[i]=(int)(Math.random()*500);
        }
        for(int i=0;i<tablica1.length;i++){
            if(tablica1[i]%2==0){
                System.out.print(i+" ");
            }
            
        }
        System.out.println();
        System.out.println();



        char[] chary1=new char[10];
        
        for(int i=0;i<chary1.length;i++){
            chary1[i]=(char)(Math.random()*26);
            chary1[i]+='A';
        }
        char[] chary2=new char[chary1.length];
        for(int i=0;i<chary2.length;i++){
            chary2[i]=chary1[(chary2.length-1)-i];
            
        }

        for(int i=0;i<chary2.length;i++){
            System.out.print(chary1[i]);
            System.out.println(chary2[i]);
        }
        System.out.println();
        System.out.println();



        char[] chary3={'a','b','c','e','f'};
        for(int i=0;i<chary3.length;i++){
            System.out.print(chary3[(i+4)%chary3.length]+" ");
            System.out.print(chary3[i]+" ");
            System.out.print(chary3[(i+1)%chary3.length]+"\n");
        }

        System.out.println();
        System.out.println();

        int n=9;
        char[] chary4=new char[n];
        char a='a';
        for(int i=0;i<=n/2;i++){
            chary4[(n/2)+i]=a;
            chary4[(n/2)-i]=a;
            a++;
        }
        for(int i=0;i<chary4.length;i++){
            System.out.print(chary4[i]+" ");
        }

        System.out.println();
        System.out.println();

        int[] tablica2=new int[50];
        int parzyste=0;
        int nieparzyste=0;
        for(int i=0;i<tablica2.length;i++){
            tablica2[i]=(int)(Math.random()*5);
            if(tablica2[i]%2==1)nieparzyste++;
            else parzyste++;
        }
        int[] parzystaTablica=new int[parzyste];
        int[] nieparzystaTablica=new int[nieparzyste];

        System.out.println();
        System.out.println();





        int[] duzeinty=new int[100];
        for(int i=0;i<duzeinty.length;i++){
            duzeinty[i]=(int)(Math.random()*500);
            System.out.print(duzeinty[i]+" ");
        }


        int sredniaArytmetyczna=0;
        for(int i=0;i<duzeinty.length;i++){
            sredniaArytmetyczna+=duzeinty[i];
        }
        sredniaArytmetyczna/=duzeinty.length;
        System.out.println();
        System.out.println("Srednia= "+sredniaArytmetyczna);
        System.out.println();
        System.out.println();
        int ileMniejszych=0;
        for(int i=0;i<duzeinty.length;i++){
            if(duzeinty[i]<sredniaArytmetyczna){
                ileMniejszych++;
            }
        }
        int[] tablicaMniejsza=new int[ileMniejszych];
        int[] tablicaWieksza=new int[duzeinty.length-ileMniejszych];
        int mniejsza=0;
        int wieksza=0;
        for(int i=0;i<duzeinty.length;i++){
            if(duzeinty[i]>sredniaArytmetyczna){
                tablicaWieksza[wieksza]=duzeinty[i];
                wieksza++;
            }
            else{
                tablicaMniejsza[mniejsza]=duzeinty[i];
                mniejsza++;

            }
        }
        System.out.println();
        System.out.println();
        for(int i=0;i<tablicaWieksza.length;i++){
            System.out.print(tablicaWieksza[i]+" ");
        }
        System.out.println();
        System.out.println();
        for(int i=0;i<tablicaWieksza.length;i++){
            System.out.print(tablicaMniejsza[i]+" ");
        }

    }
}