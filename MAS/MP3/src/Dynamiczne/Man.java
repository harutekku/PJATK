package Dynamiczne;

import java.time.LocalDate;

public abstract class Man{
	protected String name, surname;
	protected LocalDate dateOfBirth;
	protected int money;

	public Man(String name,String surname,LocalDate dateOfBirth,int money){
		this.name=name;
		this.surname=surname;
		this.dateOfBirth=dateOfBirth;
		this.money=money;
	}

	public abstract void goOut();

	@Override
	public String toString(){
		return this.getClass().getSimpleName()+": Name: "+name+" "+surname+" born at "+dateOfBirth.toString();
	}

}
