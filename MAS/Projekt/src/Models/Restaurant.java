package Models;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity(name="Restaurant")
public class Restaurant extends Local{
	@Basic
	public double getExciseForAlcohol(){
		return exciseForAlcohol;
	}
	private void setExciseForAlcohol(double exciseForAlcohol){
		this.exciseForAlcohol=exciseForAlcohol;
	}
	private double exciseForAlcohol;

	protected Restaurant(){}
	private Restaurant(String street,int number,String postalCode,String city,Company owner,double exciseForAlcohol){
		super(street,number,postalCode,city,owner);
		this.exciseForAlcohol=exciseForAlcohol;
	}
	public static Restaurant createRestaurant(String street,int number,String postalCode,String city,Company owner,double exciseForAlcohol){
		Restaurant restaurant=new Restaurant(street,number,postalCode,city,owner,exciseForAlcohol);
		return restaurant;
	}

}
