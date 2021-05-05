package Dynamiczne;

import java.time.LocalDate;

public class Single extends Man{
	private boolean goesToCollege;

	public Single(String name,String surname,LocalDate dateOfBirth,boolean goesToCollege,int money){
		super(name,surname,dateOfBirth,money);
		this.goesToCollege=goesToCollege;
	}

	public Single(Man previousMan,boolean goesToCollege){
		super(previousMan.name,previousMan.surname,previousMan.dateOfBirth,previousMan.money);
		this.goesToCollege=goesToCollege;
	}

	public void goOut(){
		if(goesToCollege) System.out.println("He went to study");
		else{
			if(money>50){
				System.out.println("He went to bar");
				money-=30;
			}else{
				System.out.println("He went to park");
			}
		}
	}

	@Override
	public String toString(){
		if(goesToCollege) return super.toString()+" and going to college";
		else return super.toString()+" and have a lot of free time";
	}
}
