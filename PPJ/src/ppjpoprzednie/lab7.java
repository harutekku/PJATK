public class lab7{
	public static void main(String[] args){
		//Jakub Pawłowicz s18688 grupa 11
		System.out.println();
		int wrt=6;
		System.out.println((wrt>5)?"int z przedzialu <6, 10>":"int z przedzialu <0, 5>");
		System.out.println();
		
		char chr='H';
		System.out.println((chr<='G')?"znak A - G":"znak H - Z");
		System.out.println();
		
		int liczba=43706;
		int even=0b1010101010101010;
		int odd=0b0101010101010101;
		int newEven=0;
		int newOdd=0;
		for(int i=32;i>=0;i--){
			if(i%2==0){
				newEven*=2;
				if((liczba>>i)%2==1){
					newEven++;
				}
			}
			else{
				newOdd*=2;
				if((liczba>>i)%2==1){
					newOdd++;
				}
			}
		}

		
		//System.out.println(liczba&even);
		//System.out.println(liczba&odd);
		System.out.println(newEven);
		System.out.println(newOdd);
		System.out.println();
		
		
		byte malaLiczba=15;
		char liczbaHexa;
		if(malaLiczba>=10){
			liczbaHexa=(char)malaLiczba;
			liczbaHexa-=10;
			liczbaHexa+='A';
		}
		else{
			liczbaHexa=(char)malaLiczba;
			liczbaHexa+='0';
		}
		System.out.println(liczbaHexa);
		System.out.println();
		
		for(int i=-50;i<=50;i++){
			if(i%8==0){
				System.out.println(i);
			}
		}
		System.out.println();
		
		int s=0;
		int i=1;
		while(i<=10){
			s=s+i;
			i++;
		}
		System.out.println(s);
		System.out.println();
	}
}