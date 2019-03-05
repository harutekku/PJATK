public class lab4{
	
	public static void main(String[] args){
		byte bytmaly=-128;
		byte bytduzy=127;
		short shortmaly=-32768;
		short shortduzy=32767;
		int intmaly=-2147483648;
		int intduzy=2147483647;
		long longmaly=-9223372036854775808l;
		long longduzy=9223372036854775807l;
		
		byte bdwojkowy=0b1111111;
		byte bosemkowy=033;
		byte bszesnastkowy=0x22;
		
		short sdwojkowy=0b11;
		short sosemkowy=033;
		short sszesnastkowy=0x22;
		
		int idwojkowy=0b11;
		int iosemkowy=033;
		int iszesnastkowy=0x22;
		
		long ldwojkowy=0b11;
		long losemkowy=033;
		long lszesnastkowy=0x22;
		
		double malydouble= Double.MAX_VALUE *(-1.0);
		double duzydouble=Double.MAX_VALUE;
		
		System.out.println();
		
		
		int intt=1;
		char charr='1';
		float floatt=1;
		double doublee=1;
		byte bytee=1;
		
		
		charr+=intt;
		intt+=charr;
		floatt+=doublee;
		bytee+=intt;
		System.out.println(charr +" "+intt+" "+floatt+" "+doublee+" "+bytee);
		
		System.out.println();
		int x = 2 * ((5 + 3) * 4 - 8);
		System.out.println(x);
		
		System.out.println();
		System.out.println("Podaj liczbe");
		
		java.util.Scanner in=new java.util.Scanner(System.in);
		double y=in.nextDouble();
		System.out.println();
		
		if(y<10){System.out.println("Mniejsza niz 10");}else{System.out.println("Wieksza niz 10");}
		if(y<100){System.out.println("Mniejsza niz 100");}else{System.out.println("Wieksza niz 100");}
		if(y<1000){System.out.println("Mniejsza niz 1000");}else{System.out.println("Wieksza niz 1000");}
		System.out.println();
		System.out.println();
		if(y>1){System.out.println("Nalezy do A");}else if (y<0){System.out.println("Nalezy do B");}
		else{System.out.println("Nalezy do A B i C");}
		
	}
	
	
}