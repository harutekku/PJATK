package Wieloaspektowe;

import java.time.LocalDate;

public class MildAnimal extends Animal{

	public MildAnimal(HabitatType habitatType,LocalDate dateOfFinding,double weight,double x,double y,int temperature){
		super(habitatType,dateOfFinding,weight,x,y,temperature);
	}

	public void migrate(){
		double length=getHabitatType().equals(HabitatType.Water)?Math.random()*100-50:Math.random()*10-5;
		setLocation(getLocation().x+length,getLocation().y+length);
	}
}
