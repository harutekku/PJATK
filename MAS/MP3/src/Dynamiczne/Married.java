package Dynamiczne;

import java.time.LocalDate;

public class Married extends Man{
	private String wifeName;

	public Married(String name,String surname,LocalDate dateOfBirth,String wifeName,int money){
		super(name,surname,dateOfBirth,money);
		this.wifeName=wifeName;
	}

	public Married(Man previousMan,String wifeName){
		super(previousMan.name,previousMan.surname,previousMan.dateOfBirth,previousMan.money);
		this.wifeName=wifeName;
	}

	public void goOut(){
		System.out.println("Goes to work");
		money+=3200;
	}

	@Override
	public String toString(){
		return super.toString()+" and have "+wifeName+" as wife";
	}
}
