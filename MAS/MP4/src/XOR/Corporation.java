package XOR;

public class Corporation{
	private String name;
	private Account account;

	public Corporation(String name){
		this.name=name;
	}

	public void setAccount(Account account) throws Exception{
		account.setOwner(this);
		this.account=account;
	}

	public Account getAccount(){
		return account;
	}
}
