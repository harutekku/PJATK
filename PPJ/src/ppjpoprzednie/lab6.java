public class lab6{
	public static void main(String[] args){
		byte wrt;
		System.out.println("Podaj liczbe");
		java.util.Scanner in=new java.util.Scanner(System.in);
		wrt=in.nextByte();
		
		for(int i=7;i>=0;i--){
			System.out.println(wrt&(1<<i));
			
		}
		System.out.println();
		System.out.println();
		
		int ARGB=370208;
		for(int i=24;i>=0;i-=8){
			System.out.println((ARGB>>i)&0b11111111);
		}
		
		System.out.println();
		System.out.println();
		
		//false, 20, false
		
		boolean x=true,z=true;
		int y=20;
		x=(y!=10)^(z=false);
		System.out.println(x+", "+y+", "+z);
		
		//true 20 false
		
		
		int dzien=19;
		int miesiac=10;
		
		System.out.println();
		System.out.println();
		
		int sumaDni=0;
		miesiac--;
		while(miesiac>0){
			switch(miesiac){
				case 1: case 3: case 5: case 7: case 8: case 10: case 12: sumaDni+=31; break;
				case 2: sumaDni+=28; break; 
				default: sumaDni+=30; break;
			}
			miesiac--;
			//System.out.println(sumaDni);
		}
		sumaDni+=dzien;
		System.out.println(sumaDni);
		
		for(int i=1;i<=10;i++){
			System.out.println(i*wrt);
		}
		System.out.println();
		System.out.println();
		int maslo=5;
		while(maslo<5){//nie wyswietli ani razu
			System.out.println(maslo);
			maslo++;
		}
		System.out.println();
		maslo=5;
		do{//wyswietli conajmniej raz
			System.out.println(maslo);
			maslo++;
		}while(maslo<5);
		
		
		System.out.println();
		System.out.println();
		
		
		double sumaa=0;
		for(int i=0;i<10;i++){
			int j=i;
			int potega=1;
			while(j>0){
				potega*=2;
				j--;
			}
			double szereg=(double)1/potega;
			sumaa+=szereg;
			System.out.println(sumaa);
		}
		
		System.out.println();
		System.out.println();
		
		
		for(int i=1;i<=5;i++){
			for(int j=i;j>0;j--){
				System.out.print("*");
			}
			System.out.println();
		}
		
		
	}
}

























