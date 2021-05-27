import javax.persistence.*;
import java.util.*;

enum PersonType{Person,User,Employee,Editor};

@Entity(name="Person")
public class Person{
	private static Set<String> logins=new TreeSet<>();

	public static boolean checkLogin(String login){
		return logins.contains(login);
	}

	public static void addLogin(String login){
		logins.add(login);
	}

	@ElementCollection
	@Enumerated(EnumType.STRING)
	private EnumSet<PersonType> personKind=EnumSet.of(PersonType.Person);
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private String login;
	private String password;
	private List<CreditCard> creditCards=new ArrayList<>();

	protected Person(){
	}

	private Person(String firstName,String lastName,String phoneNumber,String emailAddress,String login,String password){
		this.firstName=firstName;
		this.lastName=lastName;
		this.phoneNumber=phoneNumber;
		this.emailAddress=emailAddress;
		this.login=login;
		this.password=password;
	}

	public static Person createUser(String firstName,String lastName,String phoneNumber,String emailAddress,String login,String password){
		if(checkLogin(login)){
			throw new IllegalArgumentException("This login already exist");
		}
		addLogin(login);
		Person user=new Person(firstName,lastName,phoneNumber,emailAddress,login,password);
		user.addPersonKind(PersonType.User);
		return user;
	}

	public static Person createEditor(String firstName,String lastName,String phoneNumber,String emailAddress,String login,String password){
		if(checkLogin(login)){
			throw new IllegalArgumentException("This login already exist");
		}
		addLogin(login);
		Person editor=new Person(firstName,lastName,phoneNumber,emailAddress,login,password);
		editor.addPersonKind(PersonType.Editor);
		return editor;
	}

	public static Person createEmployee(String firstName,String lastName,String phoneNumber,String emailAddress,String login,String password){
		if(checkLogin(login)){
			throw new IllegalArgumentException("This login already exist");
		}
		addLogin(login);
		Person employee=new Person(firstName,lastName,phoneNumber,emailAddress,login,password);
		employee.addPersonKind(PersonType.Employee);
		return employee;
	}

	@OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<CreditCard> getCreditCards(){
		return creditCards;
	}

	private void setCreditCards(List<CreditCard> creditCards){
		this.creditCards=creditCards;
	}

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


	public EnumSet<PersonType> getPersonKind(){
		return personKind;
	}

	private void setPersonKind(EnumSet<PersonType> personKind){
		this.personKind=personKind;
	}

	public void addPersonKind(PersonType personType){
		this.personKind.add(personType);
	}

	public boolean checkPersonKind(PersonType personType){
		return personKind.contains(personType);
	}

	@Basic
	public String getFirstName(){
		return firstName;
	}

	private void setFirstName(String firstName){
		this.firstName=firstName;
	}

	@Basic
	public String getLastName(){
		return lastName;
	}

	private void setLastName(String lastName){
		this.lastName=lastName;
	}

	@Basic
	public String getPhoneNumber(){
		return phoneNumber;
	}

	private void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}

	@Basic
	public String getEmailAddress(){
		return emailAddress;
	}

	private void setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
	}

	@Basic
	@Id
	public String getLogin(){
		return login;
	}

	private void setLogin(String login){
		this.login=login;
	}


	@Basic
	public String getPassword(){
		return password;
	}

	private void setPassword(String password){
		this.password=password;
	}

	@Override
	public String toString(){
		return "Person{"+
				"firstName='"+firstName+'\''+
				", lastName='"+lastName+'\''+
				", phoneNumber='"+phoneNumber+'\''+
				", emailAddress='"+emailAddress+'\''+
				", login='"+login+'\''+
				'}';
	}
}
