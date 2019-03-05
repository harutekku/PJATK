public class Gr11_PPJ10_s18688 {
    public static void main(String[] args) {
        //zadanie 1
        System.out.println("Zadanie1");
        System.out.println();
        //
        float[] arr1={0,2,4,6,8,10},arr2={1,3,5,7,9};
        float[] arr3=new float[arr1.length+arr2.length];
        int iArr1=arr1.length,iArr2=arr2.length,iArr3=0;
        for(int i=0;i<arr1.length+arr2.length;i++){
            if(i<iArr1){
                arr3[iArr3]=arr1[i];
                iArr3++;
            }
            if(i<iArr2){
                arr3[iArr3]=arr2[i];
                iArr3++;
            }
        }
        for(float i:arr3) {
            System.out.print(i+" ");
        }
        System.out.println();


        //zadanie 2
        System.out.println();
        System.out.println("Zadanie2");
        System.out.println();
        //
        
        int [] arr4={1,2,3,4,4,3,2,1};
        boolean anagram=true;
        for(int i=0;i<arr4.length/2;i++){
            if(arr4[i]!=arr4[arr4.length-1-i]){
                anagram=false;
            }
        }
        if(anagram)System.out.println("True");
        else System.out.println("False");
        

        //zadanie 3
        System.out.println();
        System.out.println("Zadanie3");
        System.out.println();
        //
        
        double[] arr5=new double[10];
        for(int i=0;i<arr5.length;i++){
            arr5[i]=((double)((int)((Math.random()*20-10)*100)))/100;
        }
        for(double i:arr5){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println();
        for(int i=0;i<arr5.length;i++){
            boolean mniejsze=true;
            for(int j=0;j<i;j++){
                if(arr5[j]>arr5[i])mniejsze=false;
            }
            if(mniejsze){
                System.out.println(i);
                for(int j=0;j<=i;j++){
                    System.out.print(arr5[j]+" ");
                }
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
        for(int i=0;i<arr5.length;i++){
            boolean wieksze=true;
            for(int j=arr5.length-1;j>i;j--){
                if(arr5[j]<arr5[i])wieksze=false;
            }
            if(wieksze){
                System.out.println(i);
                for(int j=arr5.length-1;j>=i;j--){
                    System.out.print(arr5[j]+" ");
                }
                System.out.println();
            }
        }
        
        //zadanie 4
        System.out.println();
        System.out.println("Zadanie4");
        System.out.println();
        //
        
        char[] arr6=new char[10];
        for(int i=0;i<arr6.length;i++){
            arr6[i]=(char)(Math.random()*26);
            arr6[i]+='a';
        }
        for(char a:arr6){
            System.out.print(a+" ");
        }
        System.out.println();
        System.out.println();
        int[] ileCharow=new int[26];
        for(int i=0;i<arr6.length;i++){
            int litera=arr6[i];
            litera-='a';
            ileCharow[litera]++;
        }
        for(char i=0;i<ileCharow.length;i++){
            if(ileCharow[i]>0)System.out.print(((char)(i+'a'))+" "+ileCharow[i]+"\t");
        }
        System.out.println();

        //zadanie 5
        System.out.println();
        System.out.println("Zadanie5");
        System.out.println();
        //
        
        byte[] arr7=new byte[10];
        int ileParzystych=0;
        for(int i=0;i<arr7.length;i++){
            arr7[i]=(byte)(Math.random()*5);
            if(arr7[i]%2==0)ileParzystych++;
        }
        byte[] arr8=new byte[ileParzystych];
        byte[] arr9=new byte[arr7.length-ileParzystych];
        int parzyste=0,nieparzyste=0;
        for(int i=0;i<arr7.length;i++){
            if(arr7[i]%2==0){
                arr8[parzyste]=arr7[i];
                parzyste++;
            }
            else{
                arr9[nieparzyste]=arr7[i];
                nieparzyste++;
            }
        }
        for(byte a:arr7){
            System.out.print(a+" ");
        }
        System.out.println();
        
        for(byte a:arr8){
            System.out.print(a+" ");
        }
        System.out.println();
        
        for(byte a:arr9){
            System.out.print(a+" ");
        }
        System.out.println();

        //zadanie 6
        System.out.println();
        System.out.println("Zadanie6");
        System.out.println();
        //

        int[][] tab={
            {1,0,0,0,0},
            {0,1,0,0},
            {0,0,1}
        };

        int howMuch=0;
        for(int i=0;i<tab.length;i++){
            howMuch+=tab[i].length;
        }
        int[] tab2=new int[howMuch];
        int iTab2=0;
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[i].length;j++){
                tab2[iTab2]=tab[i][j];
                iTab2++;
            }
        }
        for(int a:tab2){
            System.out.print(a+" ");
        }


    }
    
}