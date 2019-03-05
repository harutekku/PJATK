public class g11s18688 {
    public static void main(String[] args) {
        // ========== ćw 1 =============
        int[] tab1=new int[(int)(Math.random()*5+5)];
        int[] tab2=new int[(int)(Math.random()*5+5)];
        int[] tab3=new int[(int)(Math.random()*5+5)];

        for(int i=0;i<tab1.length;i++)tab1[i]=(int)(Math.random()*20);
        for(int i=0;i<tab2.length;i++)tab2[i]=(int)(Math.random()*20);
        for(int i=0;i<tab3.length;i++)tab3[i]=(int)(Math.random()*20);

        int[][] tab4={tab1,tab2,tab3};

        for(int i=0;i<tab4.length;i++){
            for(int a:tab4[i]){
                System.out.print(a+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();


        // ========== ćw 2 =============

        int[][] tab5=new int[8][8];
        for(int i=0;i<tab5.length;i++){
            for(int j=0;j<tab5[i].length;j++){
                tab5[i][j]=(int)(Math.random()*10);
            }
        }
        boolean if3=false;
        for(int i=0;i<tab5.length;i++){
            
        }




        // ========== ćw 3 =============




        // ========== ćw 4 =============
    }
}