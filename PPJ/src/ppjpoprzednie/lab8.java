public class lab8{
	public static void main(String[] args){
		int[] tablica={2,3};
		
		
		int[] tab=new int[10];
		System.out.println(tab.length);
		System.out.println();
		
		
		int[] losowa=new int[10];
		int ileJedynek=0,ileZer=0;
		for(int i=0;i<losowa.length;i++){
			losowa[i]=(int)(Math.random()*2);
			//System.out.println(losowa[i]);
			ileJedynek+=(losowa[i]==1)?1:0;
			ileZer+=(losowa[i]==0)?1:0;
		}
		
		System.out.println(ileZer);
		System.out.println(ileJedynek);
		System.out.println();
		
		
		double[] nowyDouble=new double[10];
		for(int i=0;i<nowyDouble.length;i++){
			nowyDouble[i]=(Math.random()*10);		
		}
		
		
		for(int i=0;i<nowyDouble.length;i++){
			System.out.print(nowyDouble[i]+" ");
		}
		System.out.println();
		System.out.println();
		
		
		for(int i=0;i<nowyDouble.length;i+=2){
			System.out.print(nowyDouble[i]+" ");
		}
		System.out.println();
		System.out.println();
		
		for(int i=0;i<nowyDouble.length;i++){
			System.out.print( ( ( ( (int)nowyDouble[i]) %2==1)?nowyDouble[i]:"")+" ");
		}
		System.out.println();
		System.out.println();
		
		
		char[] znaki={'a','F','t'};
		int najmniejszyIndeks=0;
		for(int i=0;i<znaki.length;i++){
			if(znaki[najmniejszyIndeks]>znaki[i]){
				najmniejszyIndeks=i;
			}
		}
		System.out.println(najmniejszyIndeks);
		System.out.println();
		System.out.println();
		
		
		
		int tab2[]={789,678,567};
		for(int i=0;i<tab2.length;i++)
			for(int j=i;j<tab2.length;j++)System.out.println(tab2[i]-tab2[j]);
		//Odejmie każdą liczbe od każdej i wypisze wynik odejmowania
		System.out.println();
		System.out.println();
		
		byte[] byt1={1,2,3};
		byte[] byt2={1,2,3};
		if((byt1[0]==byt2[0])&&(byt1[1]==byt2[1])&&(byt1[2]==byt2[2])){
			System.out.println("Takie same");
		}
		else System.out.println("Nie takie same");
		System.out.println();
		System.out.println();
		
		
		char[] wieleZnakow=new char[5];
		for(int i=0;i<wieleZnakow.length;i++){
			wieleZnakow[i]=(char)(Math.random()*26);
			wieleZnakow[i]+='A';
			System.out.print(wieleZnakow[i]+" ");
		}
		System.out.println();
		
		java.util.Scanner in=new java.util.Scanner(System.in);
		while(!(wieleZnakow[0]==0 && wieleZnakow[1]==0 && wieleZnakow[2]==0 && wieleZnakow[3]==0 && wieleZnakow[4]==0)){
			int ileRazyPowtorzony=0;
			char c=in.next().charAt(0);
			for(int i=0;i<wieleZnakow.length;i++){
				if(wieleZnakow[i]==c){
					ileRazyPowtorzony++;
					wieleZnakow[i]=0;
				}				
			}
			System.out.print(ileRazyPowtorzony+"   "+wieleZnakow[0]+" "+wieleZnakow[1]+" "+wieleZnakow[2]+" "+wieleZnakow[3]+" "+wieleZnakow[4]+"\n");
		}




		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		
		
	}
}