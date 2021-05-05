package Wielodziedziczenie;

import java.math.BigDecimal;

public class Cashier extends Employee implements ICashier{
	private int shiftLength;

	public Cashier(String firstName,String lastName,String address,BigDecimal salary,int shiftLength){
		super(firstName,lastName,address,salary);
		setShiftLength(shiftLength);
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
	public void promote(){
		setSalary(getSalary().multiply(new BigDecimal(1.1)));
	}

	@Override
	public void demote(){
		setSalary(getSalary().multiply(new BigDecimal(0.9)));
	}

	@Override
	public BigDecimal getSalary(){
		return super.getSalary();
	}

	@Override
	public String toString(){
		return super.toString()+", shiftLength: "+getShiftLength();
	}
}
