package Models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

/**
 * The enum Person type.
 */
enum PersonType{
	/**
	 * Person person type.
	 */
	Person,
	/**
	 * User person type.
	 */
	User,
	/**
	 * Employee person type.
	 */
	Employee,
	/**
	 * Editor person type.
	 */
	Editor,
	/**
	 * Operator person type.
	 */
	Operator};

/**
 * The type Person.
 */
@Entity(name="Person")
public class Person{
	private static Set<String> logins=new TreeSet<>();
	/**
	 * Check login boolean.
	 *
	 * @param login the login
	 * @return the boolean
	 */
	public static boolean checkLogin(String login){
		return logins.contains(login);
	}
	/**
	 * Add login.
	 *
	 * @param login the login
	 */
	public static void addLogin(String login){
		logins.add(login);
	}

	/**
	 * Get login string.
	 *
	 * @return the string
	 */
	@Basic
	@Id
	public String getLogin(){
		return login;
	}
	private void setLogin(String login){
		this.login=login;
	}
	private String login;

	/**
	 * Get first name string.
	 *
	 * @return the string
	 */
	@Basic
	public String getFirstName(){
		return firstName;
	}
	private void setFirstName(String firstName){
		this.firstName=firstName;
	}
	private String firstName;

	/**
	 * Get last name string.
	 *
	 * @return the string
	 */
	@Basic
	public String getLastName(){
		return lastName;
	}
	private void setLastName(String lastName){
		this.lastName=lastName;
	}
	private String lastName;

	/**
	 * Get phone number string.
	 *
	 * @return the string
	 */
	@Basic
	public String getPhoneNumber(){
		return phoneNumber;
	}
	private void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	private String phoneNumber;

	/**
	 * Get email address string.
	 *
	 * @return the string
	 */
	@Basic
	public String getEmailAddress(){
		return emailAddress;
	}
	private void setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
	}
	private String emailAddress;

	/**
	 * Get password string.
	 *
	 * @return the string
	 */
	@Basic
	public String getPassword(){
		return password;
	}
	private void setPassword(String password){
		this.password=password;
	}
	private String password;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private EnumSet<PersonType> personKind=EnumSet.of(PersonType.Person);
	/**
	 * Get person kind enum set.
	 *
	 * @return the enum set
	 */
	public EnumSet<PersonType> getPersonKind(){
		return personKind;
	}
	private void setPersonKind(EnumSet<PersonType> personKind){
		this.personKind=personKind;
	}
	/**
	 * Add person kind.
	 *
	 * @param personType the person type
	 */
	public void addPersonKind(PersonType personType){
		this.personKind.add(personType);
	}
	/**
	 * Check person kind boolean.
	 *
	 * @param personType the person type
	 * @return the boolean
	 */
	public boolean checkPersonKind(PersonType personType){
		return personKind.contains(personType);
	}

	/**
	 * Get credit cards list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<CreditCard> getCreditCards(){
		return creditCards;
	}
	private void setCreditCards(List<CreditCard> creditCards){
		this.creditCards=creditCards;
	}
	/**
	 * Add card.
	 *
	 * @param card the card
	 * @throws Exception the exception
	 */
	public void addCard(CreditCard card) throws Exception{
		if(personKind.contains(PersonType.User)){
			if(card.getOwner().equals(this)){
				creditCards.add(card);
			}else{
				throw new Exception("Card is not owned by this user");
			}
		}else{
			throw new Exception("This person cant have card");
		}
	}
	private List<CreditCard> creditCards=new ArrayList<>();

	/**
	 * Get orders list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE, orphanRemoval=true)
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
		if(personKind.contains(PersonType.User)){
			if(order.getUser().equals(this)){
				orders.add(order);
			}else{
				throw new Exception("Models.Order is not owned by this user");
			}
		}else{
			throw new Exception("This person cant have orders");
		}
	}
	private List<Order> orders=new ArrayList<>();

	/**
	 * Get orders produced list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="producer", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<Order> getOrdersProduced(){
		return ordersProduced;
	}
	private void setOrdersProduced(List<Order> ordersProduced){
		this.ordersProduced=ordersProduced;
	}
	/**
	 * Add order produced.
	 *
	 * @param order the order
	 * @throws Exception the exception
	 */
	public void addOrderProduced(Order order) throws Exception{
		if(personKind.contains(PersonType.Employee)||personKind.contains(PersonType.Operator)){
			if(order.getProducer().equals(this)){
				ordersProduced.add(order);
			}else{
				throw new Exception("Models.Order is not produced by this user");
			}
		}else{
			throw new Exception("This person cant produced orders");
		}
	}
	private List<Order> ordersProduced=new ArrayList<>();

	/**
	 * Get orders operated list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="operator", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<Order> getOrdersOperated(){
		return ordersOperated;
	}
	private void setOrdersOperated(List<Order> ordersOperated){
		this.ordersOperated=ordersOperated;
	}
	/**
	 * Add order operated.
	 *
	 * @param order the order
	 * @throws Exception the exception
	 */
	public void addOrderOperated(Order order) throws Exception{
		if(personKind.contains(PersonType.Operator)){
			if(order.getOperator().equals(this)){
				ordersOperated.add(order);
			}else{
				throw new Exception("Models.Order is not operated by this user");
			}
		}else{
			throw new Exception("This person cant operate orders");
		}
	}
	private List<Order> ordersOperated=new ArrayList<>();

	/**
	 * Get offers list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="author", cascade=CascadeType.REMOVE, orphanRemoval=true)
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
		if(personKind.contains(PersonType.Editor)){
			if(offer.getAuthor().equals(this)){
				offers.add(offer);
			}else{
				throw new Exception("Models.Offer is not created by this author");
			}
		}else{
			throw new Exception("This person cant create offer");
		}
	}
	private List<Offer> offers=new ArrayList<>();

	/**
	 * Get reviews list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="reviewer", cascade=CascadeType.REMOVE, orphanRemoval=true)
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
		if(personKind.contains(PersonType.User)){
			if(review.getReviewer().equals(this)){
				reviews.add(review);
			}else{
				throw new Exception("Models.Review is not created by this user");
			}
		}else{
			throw new Exception("This person cant create reviews");
		}
	}
	private List<Review> reviews=new ArrayList<>();

	/**
	 * Get work local.
	 *
	 * @return the local
	 */
	@ManyToOne
	public Local getWork(){
		return work;
	}
	/**
	 * Set work.
	 *
	 * @param local the local
	 */
	public void setWork(Local local){
		this.work=work;
	}
	private Local work;


	/**
	 * Instantiates a new Person.
	 */
	protected Person(){}
	private Person(String firstName,String lastName,String phoneNumber,String emailAddress,String login,String password){
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.emailAddress=emailAddress;
		this.login=login;
		this.password=password;
	}

	/**
	 * Create user person.
	 *
	 * @param firstName    the first name
	 * @param lastName     the last name
	 * @param phoneNumber  the phone number
	 * @param emailAddress the email address
	 * @param login        the login
	 * @param password     the password
	 * @return the person
	 */
	public static Person createUser(String firstName,String lastName,String phoneNumber,String emailAddress,String login,String password){
		if(checkLogin(login)){
			throw new IllegalArgumentException("This login already exist");
		}
		addLogin(login);
		Person user=new Person(firstName,lastName,phoneNumber,emailAddress,login,password);
		user.addPersonKind(PersonType.User);
		return user;
	}
	/**
	 * Create editor person.
	 *
	 * @param firstName    the first name
	 * @param lastName     the last name
	 * @param phoneNumber  the phone number
	 * @param emailAddress the email address
	 * @param login        the login
	 * @param password     the password
	 * @return the person
	 */
	public static Person createEditor(String firstName,String lastName,String phoneNumber,String emailAddress,String login,String password){
		if(checkLogin(login)){
			throw new IllegalArgumentException("This login already exist");
		}
		addLogin(login);
		Person editor=new Person(firstName,lastName,phoneNumber,emailAddress,login,password);
		editor.addPersonKind(PersonType.Editor);
		return editor;
	}
	/**
	 * Create producer person.
	 *
	 * @param firstName    the first name
	 * @param lastName     the last name
	 * @param phoneNumber  the phone number
	 * @param emailAddress the email address
	 * @param login        the login
	 * @param password     the password
	 * @return the person
	 */
	public static Person createProducer(String firstName,String lastName,String phoneNumber,String emailAddress,String login,String password){
		if(checkLogin(login)){
			throw new IllegalArgumentException("This login already exist");
		}
		addLogin(login);
		Person producer=new Person(firstName,lastName,phoneNumber,emailAddress,login,password);
		producer.addPersonKind(PersonType.Employee);
		return producer;
	}
	/**
	 * Create operator person.
	 *
	 * @param firstName    the first name
	 * @param lastName     the last name
	 * @param phoneNumber  the phone number
	 * @param emailAddress the email address
	 * @param login        the login
	 * @param password     the password
	 * @return the person
	 */
	public static Person createOperator(String firstName,String lastName,String phoneNumber,String emailAddress,String login,String password){
		if(checkLogin(login)){
			throw new IllegalArgumentException("This login already exist");
		}
		addLogin(login);
		Person operator=new Person(firstName,lastName,phoneNumber,emailAddress,login,password);
		//operator.addPersonKind(PersonType.Employee);
		operator.addPersonKind(PersonType.Operator);
		return operator;
	}

	@Override
	public String toString(){
		return "Models.Person{"+
				"firstName='"+firstName+'\''+
				", lastName='"+lastName+'\''+
				", phoneNumber='"+phoneNumber+'\''+
				", emailAddress='"+emailAddress+'\''+
				", login='"+login+'\''+
				'}';
	}

}
