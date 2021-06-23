import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Local")
@Inheritance(strategy=InheritanceType.JOINED)
public class Local{
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	public long getId(){
		return id;
	}
	private void setId(long id){
		this.id=id;
	}
	private long id;

	@Basic
	public String getStreet(){
		return street;
	}
	private void setStreet(String street){
		this.street=street;
	}
	private String street;

	@Basic
	public int getNumber(){
		return number;
	}
	private void setNumber(int number){
		this.number=number;
	}
	private int number;

	@Basic
	public String getPostalCode(){
		return postalCode;
	}
	public void setPostalCode(String postalCode){
		this.postalCode=postalCode;
	}
	private String postalCode;

	@Basic
	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city=city;
	}
	private String city;

	@ManyToOne()
	public Company getOwner(){
		return owner;
	}
	public void setOwner(Company owner){
		this.owner=owner;
	}
	private Company owner;

	@OneToMany(mappedBy="local", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<Offer> getOffers(){
		return offers;
	}
	private void setOffers(List<Offer> offers){
		this.offers=offers;
	}
	public void addOffer(Offer offer) throws Exception{
		if(offer.getLocal().equals(this)){
			offers.add(offer);
		}else{
			throw new Exception("Offer is not owned by this local");
		}
	}
	private List<Offer> offers=new ArrayList<>();

	protected Local(){}
	protected Local(/*long id,*/String street,int number,String postalCode,String city,Company owner){
		//this.id=id;
		this.street=street;
		this.number=number;
		this.postalCode=postalCode;
		this.city=city;
		this.owner=owner;
	}
	public static Local createLocal(String street,int number,String postalCode,String city,Company owner){
		Local local=new Local(street,number,postalCode,city,owner);
		return local;
	}

}
