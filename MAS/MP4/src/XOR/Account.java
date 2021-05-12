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
		if(ownerC==null)ownerP=person;
		else throw new Exception("Already have Corporation owner");
		System.out.println("Set owner to Person");
	}
	public void setOwner(Corporation corporation) throws Exception{
		if(ownerP==null)ownerC=corporation;
		else throw new Exception("Already have Person owner");
		System.out.println("Set owner to Corporation");
	}
}
