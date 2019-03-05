public class Program{ 

    public static void main(String[] args){ 

		System.out.println("Hello PPJ Gr 11c");
		
		boolean logiczny=true;
		int calkowite=5;
		double rzeczywiste=12.3;
		String tekst="Halko";
		
		System.out.println(logiczny);
		System.out.println(calkowite);
		System.out.println(rzeczywiste);
		System.out.println(tekst);

		
		System.out.println("Podaj numer studenta");
		
		java.util.Scanner in=new java.util.Scanner(System.in);
		int studentId=in.nextInt();
		
		System.out.println("Witaj s"+studentId);
		
		int c255;
		//int 2cc;
		//int @css;
		//int static;
		//int null;
		
		char charValue;
		char charvalue;
		//Zrobi sie bo to rozne znaki dla komputera
		
		boolean logi=true;
		int calk=1;
		double rzeczy=1.0;
		char znak='1';
		//System.out.println(logi == calk);
		//System.out.println(logi == rzeczy);
		//System.out.println(logi == znak);
		System.out.println(calk == rzeczy);//true
		System.out.println(calk == znak);//false
		System.out.println(rzeczy == znak);//false
		
		int a=5;
		double b;
		
		b=a;
		//a=b; //blad
    }

} 