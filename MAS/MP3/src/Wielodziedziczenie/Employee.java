package Wielodziedziczenie;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Employee{
	private String firstName;
	private String lastName;
	private String address;


	private BigDecimal salary;

	public Employee(String firstName,String lastName,String address,BigDecimal salary){
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.salary=salary.setScale(2,RoundingMode.CEILING);
	}

	public abstract void promote() throws Exception;

	public abstract void demote() throws Exception;

	public BigDecimal getSalary(){
		return salary;
	}

	public void setSalary(BigDecimal salary){
		this.salary=salary.setScale(2,RoundingMode.CEILING);
	}

	@Override
	public String toString(){
		return "Wielodziedziczenie.Employee data: firstName='"+firstName+"', lastName='"+lastName+"', address='"+address+"', salary="+salary;
	}

}
