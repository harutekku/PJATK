package XOR;

public class Person{
	private String name, surname;
	private Account account;

	public Person(String name,String surname){
		this.name=name;
		this.surname=surname;
	}

	public void setAccount(Account account) throws Exception{
		if(this.account==account) return;
		this.account=account;
		if(account!=null) account.setOwner(this);
	}

	public Account getAccount(){
		return account;
	}
}
