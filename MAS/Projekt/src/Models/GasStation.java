package Models;

import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 * The type Gas station.
 */
@Entity(name="GasStation")
public class GasStation extends Local{
	/**
	 * Get excise for fuel double.
	 *
	 * @return the double
	 */
	@Basic
	public double getExciseForFuel(){
		return exciseForFuel;
	}
	private void setExciseForFuel(double exciseForFuel){
		this.exciseForFuel=exciseForFuel;
	}
	private double exciseForFuel;

	/**
	 * Instantiates a new Gas station.
	 */
	protected GasStation(){}
	private GasStation(String street,int number,String postalCode,String city,Company owner,double exciseForFuel){
		super(street,number,postalCode,city,owner);
		this.exciseForFuel=exciseForFuel;
	}
	/**
	 * Create gas station gas station.
	 *
	 * @param street        the street
	 * @param number        the number
	 * @param postalCode    the postal code
	 * @param city          the city
	 * @param owner         the owner
	 * @param exciseForFuel the excise for fuel
	 * @return the gas station
	 */
	public static GasStation createGasStation(String street,int number,String postalCode,String city,Company owner,double exciseForFuel){
		GasStation gasStation=new GasStation(street,number,postalCode,city,owner,exciseForFuel);
		return gasStation;
	}

}
