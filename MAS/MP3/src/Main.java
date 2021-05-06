import Dynamiczne.*;
import Overlapping.*;
import Wieloaspektowe.*;
import Wielodziedziczenie.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class Main{
	public static void main(String[] args){
		//overlapping
		Contractor contractor1=Contractor.createCustomer("Adam","Jabłoński","Brzozowa 12");
		Contractor contractor2=Contractor.createSupplier("Stasio","Brzozowski","1234567890");
		Contractor contractor3=Contractor.createPartner("Rafał","Mazur","1234567890","Lipowa 14");
		System.out.println(contractor1);
		System.out.println(contractor2);
		System.out.println(contractor3);
		System.out.println();

		//wielodziedziczenie
		try{
			Storekeeper employee1=new Storekeeper("Rysio","Bogdan","Malinowa 4",new BigDecimal(3201),LicenseType.Basic);
			System.out.println(employee1);
			employee1.promote();
			System.out.println(employee1);
			employee1.demote();
			System.out.println(employee1);
			Cashier employee2=new Cashier("Bogdan","Rysio","Czereśniowa",new BigDecimal(3202),8);
			System.out.println(employee2);
			Laborer employee3=new Laborer("Magdalena","Znana","Prosiaczkowa",new BigDecimal(4000),LicenseType.Forklift,4);
			System.out.println(employee3);
			System.out.println();
		}catch(Exception e){
			e.printStackTrace();
		}

		//wieloaspektowe
		Random random=new Random();
		for(int i=0;i<50;i++)
			Animal.addMild(new MildAnimal(HabitatType.values()[random.nextInt(HabitatType.values().length)],LocalDate.now(),random.nextDouble()*50,random.nextDouble()*100-50,random.nextDouble()*100-50,random.nextInt(30)+10));
		AggressiveAnimal tiger=new AggressiveAnimal(HabitatType.Terrain,LocalDate.now(),47,0,0,45);
		AggressiveAnimal tiger2=new AggressiveAnimal(HabitatType.Terrain,LocalDate.now(),47,0,0,36);
		System.out.println(tiger2);
		tiger2.hunt();
		System.out.println(tiger);
		tiger.hunt();
		System.out.println(tiger);
		tiger.hunt();
		for(int i=0;i<Animal.getMilds().size();i++) Animal.getMilds().get(i).migrate();
		System.out.println("Migrating mild animals");
		tiger.hunt();
		System.out.println();


		//dynamiczne o raz abstrakcyjna klasa i wołanie metod
		Man man1=new Soldier("Stanislaw","Wokulski",LocalDate.of(1990,5,5),1000);
		man1.goOut();
		System.out.println(man1);
		man1=new Single(man1,true);
		man1.goOut();
		System.out.println(man1);
		man1=new Married(man1,"Marysia");
		man1.goOut();
		System.out.println(man1);
		System.out.println();


	}
}
