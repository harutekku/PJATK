package Dynamiczne;

import java.time.LocalDate;

public class Soldier extends Man{
	private int degree;

	public Soldier(String name,String surname,LocalDate dateOfBirth,int money,int degree){
		super(name,surname,dateOfBirth,money);
		this.degree=degree;
	}

	public Soldier(String name,String surname,LocalDate dateOfBirth,int money){
		super(name,surname,dateOfBirth,money);
		this.degree=1;
	}

	public Soldier(Man previousMan){
		super(previousMan.name,previousMan.surname,previousMan.dateOfBirth,previousMan.money);
		this.degree=1;
	}

	@Override
	public void goOut(){
		System.out.println("Goes to training");
		degree++;
	}

	@Override
	public String toString(){
		if(degree>5) return super.toString()+" and is the captain";
		else return super.toString()+" and have "+degree+" degree";
	}
}
