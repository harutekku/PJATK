package Models;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * The type Restaurant.
 */
@Entity(name="Restaurant")
public class Restaurant extends Local{
	/**
	 * Get excise for alcohol double.
	 *
	 * @return the double
	 */
	@Basic
	public double getExciseForAlcohol(){
		return exciseForAlcohol;
	}
	private void setExciseForAlcohol(double exciseForAlcohol){
		this.exciseForAlcohol=exciseForAlcohol;
	}
	private double exciseForAlcohol;

	/**
	 * Instantiates a new Restaurant.
	 */
	protected Restaurant(){}
	private Restaurant(String street,int number,String postalCode,String city,Company owner,double exciseForAlcohol){
		super(street,number,postalCode,city,owner);
		this.exciseForAlcohol=exciseForAlcohol;
	}
	/**
	 * Create restaurant restaurant.
	 *
	 * @param street           the street
	 * @param number           the number
	 * @param postalCode       the postal code
	 * @param city             the city
	 * @param owner            the owner
	 * @param exciseForAlcohol the excise for alcohol
	 * @return the restaurant
	 */
	public static Restaurant createRestaurant(String street,int number,String postalCode,String city,Company owner,double exciseForAlcohol){
		Restaurant restaurant=new Restaurant(street,number,postalCode,city,owner,exciseForAlcohol);
		return restaurant;
	}

}
