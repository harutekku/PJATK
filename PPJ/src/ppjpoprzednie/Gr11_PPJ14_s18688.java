public class Gr11_PPJ14_s18688 {
    public static void square(int a, char b){
        boolean znak=b=='o'?true:false;
        for(int i=0;i<a;i++){
            for(int j=0;j<a;j++){
                System.out.print(znak==true?'o':'x');
                znak=znak==true?false:true;
            }
            System.out.println();
        }
    }
    public static boolean czyWieksze(char a, char b, char c){
        if(b>a && c>b)return true;
        return false;
    }
    public static boolean jestRowna(int[][] a, int[][] b){
        if(a.length!=b.length || a[0].length!=b[0].length){
            return false;
        }
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(a[i][j]!=b[i][j])return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        square(5, 'o');
        System.out.println();
        square(5, 'x');
        System.out.println();
        square(6, 'o');
        System.out.println();
        square(6, 'x');
        System.out.println();
        System.out.println();
        System.out.println();

        char[][] losowe=new char[7][7];
        for(int i=0;i<losowe.length;i++){
            for(int j=0;j<losowe.length;j++){
                losowe[i][j]=(char)(Math.random()*26+'a');
            }
        }

        for(int i=0;i<losowe.length;i++){
            for(int j=0;j<losowe.length;j++){
                System.out.print(losowe[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        for(int i=0;i<losowe.length;i++){
            if(i==0){
                if(czyWieksze(losowe[1][0], losowe[0][0], losowe[0][1])){
                    System.out.println("Znaleziono trzy wieksze zaczynajac od wiersza 1 i kolumny 0 lewy gorny naroznik");
                }
            }
            else if(i==losowe.length-1){
                if(czyWieksze(losowe[0][i-2], losowe[0][i-1], losowe[1][i-1])){
                    System.out.println("Znaleziono trzy wieksze zaczynajac od wiersza 0 i kolumny "+(i-2)+" prawy gorny naroznik");
                }
                if(czyWieksze(losowe[i-1][0], losowe[i][0], losowe[i][1])){
                    System.out.println("Znaleziono trzy wieksze zaczynajac od wiersza "+(losowe.length-2)+" i kolumny 0 lewy dolny naroznik");
                }
                if(czyWieksze(losowe[i-1][i], losowe[i][i], losowe[i][i-1])){
                    System.out.println("Znaleziono trzy wieksze zaczynajac od wiersza "+(losowe.length-2)+" i kolumny "+(losowe.length-1)+" prawy dolny naroznik");
                }
            }
            else{
                if(czyWieksze(losowe[0][i-1], losowe[0][i], losowe[0][i+1]) ){
                    System.out.println("Znaleziono trzy wieksze zaczynajac od wiersza 0 i kolumny "+(i-1)+" gorna");
                }
                if(czyWieksze(losowe[losowe.length-1][i-1], losowe[losowe.length-1][i], losowe[losowe.length-1][i+1]) ){
                    System.out.println("Znaleziono trzy wieksze zaczynajac od wiersza "+(losowe.length-1)+ " i kolumny "+(i-1)+" dolna");
                }
                if(czyWieksze(losowe[i-1][losowe.length-1], losowe[i][losowe.length-1], losowe[i+1][losowe.length-1]) ){
                    System.out.println("Znaleziono trzy wieksze zaczynajac od wiersza "+(i-1)+ " i kolumny "+(losowe.length-1)+" prawa");
                }
                if(czyWieksze(losowe[i-1][0], losowe[i][0], losowe[i+1][0]) ){
                    System.out.println("Znaleziono trzy wieksze zaczynajac od wiersza "+(i-1)+ " i kolumny 0 lewa");
                }
            }
        }
        


        System.out.println();
        System.out.println();
        System.out.println();


        int [][] tab1={{1,2,3},{4,5,6},{7,8,9}},tab2={{1,2,3},{4,5,6},{7,8,9}};

        System.out.println(jestRowna(tab1, tab2));

        System.out.println();
        System.out.println();
        System.out.println();


        double[][] pary=new double[10][2];
        double srednia=0;
        for(int i=0;i<pary.length;i++){
            for(int j=0;j<pary[0].length;j++){
                pary[i][j]=(Math.random()*100);
                srednia+=pary[i][j];
            }
        }
        srednia/=(pary.length*pary[0].length);
        

        System.out.println("Srednia: "+srednia);
        for(int i=0;i<pary.length;i++){
            if(pary[i][0]<srednia){
                System.out.println(pary[i][0]+" "+pary[i][1]);
            }
        }

    }
    
}