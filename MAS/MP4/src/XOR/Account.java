package XOR;

public class Account{
	private String ID;
	private int value;
	private String password;
	private Person ownerP;
	private Corporation ownerC;

	public Account(String ID,int value,String password,Person ownerP){
		this.ID=ID;
		this.value=value;
		this.password=password;
		try{
			setOwner(ownerP);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public Account(String ID,int value,String password,Corporation ownerC){
		this.ID=ID;
		this.value=value;
		this.password=password;
		try{
			setOwner(ownerC);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void setOwner(Person person) throws Exception{
		if(ownerC==null){
			if(ownerP==null){
				ownerP=person;
				person.setAccount(this);
			}else if(ownerP==person){
				return;
			}else{
				ownerP.setAccount(null);
				ownerP=person;
				person.setAccount(this);
			}
			ownerP=person;
			if(person.getAccount()!=this){
				person.setAccount(this);
			}
		}else throw new Exception("Already have Corporation owner");
		System.out.println("Set owner to Person");
	}

	public void setOwner(Corporation corporation) throws Exception{
		if(ownerP==null){
			if(ownerC==null){
				ownerC=corporation;
				corporation.setAccount(this);
			}else if(ownerC==corporation){
				return;
			}else{
				ownerC.setAccount(null);
				ownerC=corporation;
				corporation.setAccount(this);
			}
			ownerC=corporation;
			if(corporation.getAccount()!=this){
				corporation.setAccount(this);
			}
		}else throw new Exception("Already have Person owner");
		System.out.println("Set owner to Corporation");
	}
}
