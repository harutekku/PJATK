package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * The type Local.
 */
@Entity(name="Local")
@Inheritance(strategy=InheritanceType.JOINED)
public class Local{
	/**
	 * Get id long.
	 *
	 * @return the long
	 */
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

	/**
	 * Get street string.
	 *
	 * @return the string
	 */
	@Basic
	public String getStreet(){
		return street;
	}
	private void setStreet(String street){
		this.street=street;
	}
	private String street;

	/**
	 * Get number int.
	 *
	 * @return the int
	 */
	@Basic
	public int getNumber(){
		return number;
	}
	private void setNumber(int number){
		this.number=number;
	}
	private int number;

	/**
	 * Get postal code string.
	 *
	 * @return the string
	 */
	@Basic
	public String getPostalCode(){
		return postalCode;
	}
	/**
	 * Set postal code.
	 *
	 * @param postalCode the postal code
	 */
	public void setPostalCode(String postalCode){
		this.postalCode=postalCode;
	}
	private String postalCode;

	/**
	 * Get city string.
	 *
	 * @return the string
	 */
	@Basic
	public String getCity(){
		return city;
	}
	/**
	 * Set city.
	 *
	 * @param city the city
	 */
	public void setCity(String city){
		this.city=city;
	}
	private String city;

	/**
	 * Get owner company.
	 *
	 * @return the company
	 */
	@ManyToOne()
	public Company getOwner(){
		return owner;
	}
	/**
	 * Set owner.
	 *
	 * @param owner the owner
	 */
	public void setOwner(Company owner){
		this.owner=owner;
	}
	private Company owner;

	/**
	 * Get offers list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="local", cascade=CascadeType.REMOVE, orphanRemoval=true, fetch=FetchType.EAGER)
	public List<Offer> getOffers(){
		return offers;
	}
	private void setOffers(List<Offer> offers){
		this.offers=offers;
	}
	/**
	 * Add offer.
	 *
	 * @param offer the offer
	 * @throws Exception the exception
	 */
	public void addOffer(Offer offer) throws Exception{
		if(offer.getLocal().equals(this)){
			offers.add(offer);
		}else{
			throw new Exception("Models.Offer is not owned by this local");
		}
	}
	private List<Offer> offers=new ArrayList<>();

	/**
	 * Get reviews list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="local", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<Review> getReviews(){
		return reviews;
	}
	private void setReviews(List<Review> reviews){
		this.reviews=reviews;
	}
	/**
	 * Add review.
	 *
	 * @param review the review
	 * @throws Exception the exception
	 */
	public void addReview(Review review) throws Exception{
		if(review.getLocal().equals(this)){
			reviews.add(review);
		}else{
			throw new Exception("Models.Review is not created for this local");
		}
	}
	private List<Review> reviews=new ArrayList<>();

	/**
	 * Get employees list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="work", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<Person> getEmployees(){
		return employees;
	}
	private void setEmployees(List<Person> employees){
		this.employees=employees;
	}
	/**
	 * Add employee.
	 *
	 * @param person the person
	 * @throws Exception the exception
	 */
	public void addEmployee(Person person) throws Exception{
		if(person.getWork().equals(this)){
			employees.add(person);
		}else{
			throw new Exception("Models.Person does not work here");
		}
	}
	private List<Person> employees=new ArrayList<>();

	/**
	 * Get orders list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="local", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<Order> getOrders(){
		return orders;
	}
	private void setOrders(List<Order> orders){
		this.orders=orders;
	}
	/**
	 * Add order.
	 *
	 * @param order the order
	 * @throws Exception the exception
	 */
	public void addOrder(Order order) throws Exception{
		if(order.getLocal().equals(this)){
			orders.add(order);
		}else{
			throw new Exception("Models.Order does not apply to this local");
		}
	}
	private List<Order> orders=new ArrayList<>();

	/**
	 * Instantiates a new Local.
	 */
	protected Local(){}
	/**
	 * Instantiates a new Local.
	 *
	 * @param street     the street
	 * @param number     the number
	 * @param postalCode the postal code
	 * @param city       the city
	 * @param owner      the owner
	 */
	protected Local(/*long id,*/String street,int number,String postalCode,String city,Company owner){
		//this.id=id;
		this.street=street;
		this.number=number;
		this.postalCode=postalCode;
		this.city=city;
		this.owner=owner;
	}
	/**
	 * Create local local.
	 *
	 * @param street     the street
	 * @param number     the number
	 * @param postalCode the postal code
	 * @param city       the city
	 * @param owner      the owner
	 * @return the local
	 */
	public static Local createLocal(String street,int number,String postalCode,String city,Company owner){
		Local local=new Local(street,number,postalCode,city,owner);
		return local;
	}
	/**
	 * Get actual offer items list.
	 *
	 * @return the list
	 */
	@Transient
	public List<OfferItem> getActualOfferItems(){
		Optional<Offer> offer=getOffers()
				.stream()
				.filter(o->o.getValidityEnd()==null||o.getValidityEnd().isAfter(LocalDateTime.now()))
				.findFirst();
		if(offer.isPresent()) return offer.get().getOfferItems();
		else return null;
	}

}
