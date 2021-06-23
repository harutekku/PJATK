import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity(name="GasStation")
public class GasStation extends Local{
	@Basic
	public double getExciseForFuel(){
		return exciseForFuel;
	}
	private void setExciseForFuel(double exciseForFuel){
		this.exciseForFuel=exciseForFuel;
	}
	private double exciseForFuel;

	protected GasStation(){}
	private GasStation(String street,int number,String postalCode,String city,Company owner,double exciseForFuel){
		super(street,number,postalCode,city,owner);
		this.exciseForFuel=exciseForFuel;
	}
	public static GasStation createGasStation(String street,int number,String postalCode,String city,Company owner,double exciseForFuel){
		GasStation gasStation=new GasStation(street,number,postalCode,city,owner,exciseForFuel);
		return gasStation;
	}

}
