package Overlapping;

import java.util.EnumSet;

enum ContractorType {Contractor, Customer, Supplier};
public class Contractor{
	private String firstName;
	private String lastName;


	private String NIP; //10 cyfr
	private String address;

	private EnumSet<ContractorType> contractorKind = EnumSet.of(ContractorType.Contractor);

	public Contractor(String firstName,String lastName){
		this.firstName=firstName;
		this.lastName=lastName;
	}

	public static Contractor createCustomer(String firstName,String lastName,String address){
		Contractor contractor=new Contractor(firstName,lastName);
		contractor.contractorKind.add(ContractorType.Customer);
		try{
			contractor.setAddress(address);
		}catch(Exception e){
			e.printStackTrace();
		}
		return contractor;
	}
	public static Contractor createSupplier(String firstName,String lastName,String NIP){
		Contractor contractor=new Contractor(firstName,lastName);
		contractor.contractorKind.add(ContractorType.Supplier);
		try{
			contractor.setNIP(NIP);
		}catch(Exception e){
			e.printStackTrace();
		}
		return contractor;
	}
	public static Contractor createPartner(String firstName,String lastName,String NIP,String address){
		Contractor contractor=new Contractor(firstName,lastName);
		contractor.contractorKind.add(ContractorType.Customer);
		contractor.contractorKind.add(ContractorType.Supplier);
		try{
			contractor.setNIP(NIP);
			contractor.setAddress(address);
		}catch(Exception e){
			e.printStackTrace();
		}
		return contractor;
	}

	public void setNIP(String NIP) throws Exception {
		if(contractorKind.contains(ContractorType.Supplier)) {
			this.NIP=NIP;
		}
		else {
			throw new Exception("The person is not a supplier!");
		}
	}
	public void setAddress(String address) throws Exception {
		if(contractorKind.contains(ContractorType.Customer)) {
			this.address=address;
		}
		else {
			throw new Exception("The person is not a Customer!");
		}
	}
	@Override
	public String toString(){
		String result="Overlapping.Contractor data: firstName='"+firstName+"', lastName='"+lastName+'\'';
		if(contractorKind.contains(ContractorType.Customer)) result+=", address='"+address+'\'';
		if(contractorKind.contains(ContractorType.Supplier)) result+=", NIP='"+NIP+'\'';
		return result;
	}
}