public class lab5{
	public static void main(String[] args){
		boolean czyPada=true;
		if(czyPada){
			System.out.println("Zmienna jest prawdziwa");
		}
		else{System.out.println("Zmienna jest nie prawdziwa");
		}
		System.out.println(" ");
		int wrt=0;
		System.out.println("Podaj liczbe");
		//java.util.Scanner in=new java.util.Scanner(System.in);
		//wrt=in.nextInt();
		System.out.println(" ");
		
		if(wrt<=-15){System.out.println("Wrt nalezy do B");}
		else if(wrt<=-13){System.out.println("Wrt nalezy do A i B");}
		else if(wrt<=-10){System.out.println("Wrt nalezy do A");}
		else if(wrt<=-8){System.out.println("Wrt nie nalezy do zadnego zbioru");}
		else if(wrt<=-5){System.out.println("Wrt nalezy do B");}
		else if(wrt<-4){System.out.println("Wrt nalezy do A i B");}
		else if(wrt<=-3){System.out.println("Wrt nalezy do A, B i C");}
		else if(wrt<0){System.out.println("Wrt nalezy do A i C");}
		else if(wrt<5){System.out.println("Wrt nalezy do C");}
		else if(wrt<10){System.out.println("Wrt nalezy do A i C");}
		else {System.out.println("Wrt nalezy do C");}
		System.out.println(" ");
		System.out.println(" ");
		
		int ile=0;
		if(wrt>-15&&wrt<-10){
			ile++;
		}
		if(wrt<-13){
			ile++;
		}
		if(ile==1){System.out.println("Zmienna wrt nalezy do jednego zbioru");}
		else{System.out.println("Zmienna wrt nalezy do dwoch albo zadnego zbioru");}
		
		
		System.out.println(" ");
		System.out.println(" ");
		
		double sqTwo = Math.sqrt(2);
		// moje przewidywania sa takie ze double nie jest dokladny
		
		double poKwadracie=sqTwo*sqTwo;
		poKwadracie-=2;
		System.out.println(poKwadracie);
		System.out.println(" ");
		System.out.println(" ");
		
		if(poKwadracie==0){System.out.println("Przewidywania sie nie sprawdzily");}
		else{System.out.println("Przewidywania sie sprawdzily");}
		System.out.println(" ");
		System.out.println(" ");
		
		int x=4;
		long y=x*4-x++;
		if(y<12)System.out.println("za malo");
		else System.out.println("w sam raz");
		
		//Jest w sam raz, bo jest dokladnie 12, poniewaz inkrementacja x
		//zachodzi po odjeciu x od y, czyli odejmujemy 4 zamiast 5
		
		System.out.println(" ");
		System.out.println(" ");
		
		boolean czySwieciSlonce=true;
		czyPada=true;
		
		if(czyPada&&!czySwieciSlonce){System.out.println("plucha");}
		else if(czyPada&&czySwieciSlonce){System.out.println("tecza");}
		else if(!czyPada&&czySwieciSlonce){System.out.println("slonecznie");}
		else {System.out.println("pochmurno");}
		
		System.out.println(" ");
		System.out.println(" ");
		
		int zmienna=(czyPada)?5:8;
		
		System.out.println(zmienna);
		
		
		
	}
}