package Wieloaspektowe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Animal{
	class Point{
		double x, y;

		public Point(double x,double y){
			this.x=x;
			this.y=y;
		}

		public double distance(Point a){
			double dx=x-a.x;
			double dy=y-a.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}

	private Point location;
	private HabitatType habitatType;
	private int temperature;
	private LocalDate dateOfFinding;
	private double weight;
	private static List<MildAnimal> milds=new ArrayList<>();

	public Animal(HabitatType habitatType,LocalDate dateOfFinding,double weight,double x,double y,int temperature){
		this.habitatType=habitatType;
		this.dateOfFinding=dateOfFinding;
		this.weight=weight;
		this.location=new Point(x,y);
		this.temperature=temperature;
	}

	public boolean isSick(){
		if(getHabitatType().equals(HabitatType.Water)&&temperature<15) return true;
		if(getHabitatType().equals(HabitatType.Terrain)&&temperature<30) return true;
		return false;
	}

	public static void addMild(MildAnimal mild){
		milds.add(mild);
	}

	public static List<MildAnimal> getMilds(){
		return milds;
	}

	public Point getLocation(){
		return location;
	}

	public void setLocation(double x,double y){
		location.x=x;
		location.y=y;
	}

	public HabitatType getHabitatType(){
		return habitatType;
	}

	@Override
	public String toString(){
		return "Animal: habitatType="+habitatType+", dateOfFinding="+dateOfFinding+", weight="+weight;
	}
}
