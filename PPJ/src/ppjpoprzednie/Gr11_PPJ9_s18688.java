public class Gr11_PPJ9_s18688{
    public static void main(String[] args) {
        long[] array1=new long[10];
        for (int i=0;i<array1.length;i++) {
            array1[i]=i+1;
        }
        for (long i:array1) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println();

        boolean pobliskie=true;
        while(pobliskie){
            int firstIndex=(int)(Math.random()*10);
            int secondIndex=(int)(Math.random()*10);
            long temp=array1[firstIndex];
            array1[firstIndex]=array1[secondIndex];
            array1[secondIndex]=temp;
            pobliskie=false;
            for (int i=0;i<array1.length-1;i++) {
                if((array1[i]+1==array1[i+1]||array1[i]-1==array1[i+1])){
                    pobliskie=true;
                    break;
                }
            }
            for (long i:array1) {
                System.out.print(i+" ");
            }
            System.out.println();
            
        }
        System.out.println();
        System.out.println();







        int[] array2=new int[20];
        for (int i=0;i<array2.length;i++) {
            array2[i]=(int)(Math.random()*500-250);
        }
        for (int i:array2) {
            System.out.print(i+" ");
        }
        System.out.println();
        int firstIndex=0;
        int secondIndex=0;
        for(int i=0;i<array2.length-1;i++){
            for (int j=i+1;j<array2.length;j++){
                if(Math.abs(array2[i]+array2[j])<Math.abs(array2[firstIndex]+array2[secondIndex])){
                    firstIndex=i;
                    secondIndex=j;
                }
                
                
            }
        }
        System.out.println(array2[firstIndex]+" "+array2[secondIndex]);
        System.out.println();

        System.out.println();
        System.out.println();






        int[] arr1=new int[10];
        for (int i=0;i<arr1.length;i++) {
            arr1[i]=(int)(Math.random()*100);
        }
        for (int i:arr1) {
            System.out.print(i+" ");
        }
        System.out.println();

        int[] arr2=new int[10];
        for (int i=0;i<arr2.length;i++) {
            arr2[i]=(int)(Math.random()*100);
        }
        for (int i:arr2) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println();
        
        int[] arr3=new int[arr1.length+arr2.length];
        for (int i=0;i<arr1.length;i++) {
            arr3[i]=arr1[i];
        }
        for (int i=0;i<arr2.length;i++) {
            arr3[arr1.length+i]=arr2[i];
        }
        for (int i:arr3) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println();






        int ilePowtorzen=0;
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr2.length;j++){
                if(arr1[i]==arr2[j]){
                    ilePowtorzen++;
                }
            }
        }
        int[] arr4=new int[ilePowtorzen];
        //System.out.println(ilePowtorzen);
        int indexOfArr4=0;
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr2.length;j++){
                if(arr1[i]==arr2[j]){
                    arr4[indexOfArr4]=arr1[i];
                    indexOfArr4++;
                }
            }
        }
        for (int i:arr4) {
            System.out.println(i+" ");
        }
        System.out.println();
        System.out.println();







        int minimum=arr3[0];
        int maximum=arr3[0];
        for(int i:arr3){
            if(i<minimum)minimum=i;
            if(i>maximum)maximum=i;
        }
        int ileNieZnajdujeSie=0;
        for(int j=minimum;j<=maximum;j++){
            boolean nieZnajduje=true;
            for(int i=0;i<arr3.length;i++){
                if(j==arr3[i]){
                    nieZnajduje=false;
                }
            }
            if(nieZnajduje)ileNieZnajdujeSie++;
        }

        int[] arr5=new int[ileNieZnajdujeSie];
        int indexOfArr5=0;
        for(int j=minimum;j<=maximum;j++){
            
            boolean nieZnajduje=true;
            for(int i=0;i<arr3.length;i++){
                if(j==arr3[i]){
                    nieZnajduje=false;
                }
            }
            if(nieZnajduje){
                arr5[indexOfArr5]=j;
                indexOfArr5++;
            }
        }
        for (int i:arr5) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println();
        System.out.println();






        
        int[] array3=new int[10];
        double[] array4=new double[10];
        double[] array5=new double[10];
        for(int i=0;i<array3.length;i++){
            array3[i]=(int)(Math.random()*255);
            array4[i]=Math.random()*255;
            array5[i]=array3[i]+array4[i];
        }

        for (double i:array5) {
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println();
        System.out.println();
        int temp2;
        double temp3;
        for(int i=0;i<array3.length-1;i++){
            for(int j=i+1;j<array3.length;j++){
                if(array5[i]>array5[j]){
                    temp2=array3[i];
                    temp3=array4[i];
                    array3[i]=array3[j];
                    array4[i]=array4[j];
                    array3[j]=temp2;
                    array4[j]=temp3;
                    array5[i]=array3[i]+array4[i];
                    array5[j]=array3[j]+array4[j];
                    
                }
            }
        }
        
        for(int i=0;i<array3.length;i++){
            System.out.println(array3[i]+" + "+array4[i]+" = "+array5[i]);
        }

            
        
    }
}
