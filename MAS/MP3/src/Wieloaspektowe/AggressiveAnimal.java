package Wieloaspektowe;

import java.time.LocalDate;
import java.util.Random;

public class AggressiveAnimal extends Animal{
	private int killCounter;

	public AggressiveAnimal(HabitatType habitatType,LocalDate dateOfFinding,double weight,double x,double y,int temperature){
		super(habitatType,dateOfFinding,weight,x,y,temperature);
		this.killCounter=0;
	}

	public void hunt(){
		int lilCounter=0;
		for(int i=Animal.getMilds().size()-1;i >= 0;i--){
			if(getHabitatType().equals(HabitatType.Water)){
				if(Animal.getMilds().get(i).getHabitatType().equals(HabitatType.Water)){
					if(Animal.getMilds().get(i).getLocation().distance(this.getLocation())<80){
						if(Animal.getMilds().get(i).isSick()||new Random().nextDouble()>0.6){
							lilCounter++;
							Animal.getMilds().remove(i);
						}
					}
				}
			}else{
				if(Animal.getMilds().get(i).getHabitatType().equals(HabitatType.Terrain)){
					if(Animal.getMilds().get(i).getLocation().distance(this.getLocation())<50){
						if(Animal.getMilds().get(i).isSick()||new Random().nextDouble()>0.6){
							lilCounter++;
							Animal.getMilds().remove(i);
						}
					}
				}else{
					if(Animal.getMilds().get(i).getLocation().distance(this.getLocation())<30){
						if(Animal.getMilds().get(i).isSick()||new Random().nextDouble()>0.6){
							lilCounter++;
							Animal.getMilds().remove(i);
						}
					}
				}
			}
		}
		killCounter+=lilCounter;
		System.out.println("Hunt result: "+lilCounter);
	}

	@Override
	public String toString(){
		return super.toString()+", kills="+killCounter;
	}
}
