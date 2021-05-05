package Wielodziedziczenie;

import java.math.BigDecimal;

public class Laborer extends Storekeeper implements ICashier{
	private int shiftLength;

	public Laborer(String firstName,String lastName,String address,BigDecimal salary,LicenseType licenseType,int shiftLength){
		super(firstName,lastName,address,salary,licenseType);
		this.shiftLength=shiftLength;
	}

	@Override
	public int getShiftLength(){
		return shiftLength;
	}

	@Override
	public void setShiftLength(int Length){
		this.shiftLength=Length;
	}

	@Override
	public BigDecimal getSalary(){
		return super.getSalary();
	}

	@Override
	public String toString(){
		return super.toString()+", shiftLength: "+shiftLength;
	}

}
