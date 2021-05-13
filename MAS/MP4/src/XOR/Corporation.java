package XOR;

public class Corporation{
	private String name;
	private Account account;

	public Corporation(String name){
		this.name=name;
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
