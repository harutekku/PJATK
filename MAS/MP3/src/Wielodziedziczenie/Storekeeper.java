package Wielodziedziczenie;

import java.math.BigDecimal;

public class Storekeeper extends Employee{
	private LicenseType licenseType;

	public Storekeeper(String firstName,String lastName,String address,BigDecimal salary,LicenseType licenseType){
		super(firstName,lastName,address,salary);
		this.licenseType=licenseType;
	}

	@Override
	public void promote() throws Exception{
		if(licenseType.equals(LicenseType.Forklift)){
			throw new Exception("The person is already promoted");
		}else{
			licenseType=LicenseType.Forklift;
			setSalary(getSalary().multiply(new BigDecimal(1.2)));
		}
	}

	@Override
	public void demote() throws Exception{
		if(licenseType.equals(LicenseType.Basic)){
			throw new Exception("The person is already demoted");
		}else{
			licenseType=LicenseType.Basic;
			setSalary(getSalary().multiply(new BigDecimal(0.83333334)));
		}
	}

	@Override
	public String toString(){
		return super.toString()+", Wielodziedziczenie.LicenseType: "+licenseType.toString();
	}
}
