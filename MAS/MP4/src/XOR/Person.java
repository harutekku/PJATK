package XOR;

public class Person{
	private String name, surname;
	private Account account;

	public Person(String name,String surname){
		this.name=name;
		this.surname=surname;
	}

	public void setAccount(Account account) throws Exception{
		account.setOwner(this);
		this.account=account;
	}

	public Account getAccount(){
		return account;
	}
}
