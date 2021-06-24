package Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type Credit card.
 */
@Entity(name="CreditCard")
public class CreditCard{
	private static Set<String> numbers=new TreeSet<>();
	/**
	 * Check numbers boolean.
	 *
	 * @param number the number
	 * @return the boolean
	 */
	public static boolean checkNumbers(String number){
		return numbers.contains(number);
	}
	/**
	 * Add number.
	 *
	 * @param number the number
	 */
	public static void addNumber(String number){
		numbers.add(number);
	}

	/**
	 * Get number string.
	 *
	 * @return the string
	 */
	@Id
	@Basic
	public String getNumber(){
		return number;
	}
	/**
	 * Set number.
	 *
	 * @param number the number
	 */
	public void setNumber(String number){
		this.number=number;
	}
	private String number;

	/**
	 * Get expired date local date.
	 *
	 * @return the local date
	 */
	@Basic
	public LocalDate getExpiredDate(){
		return expiredDate;
	}
	/**
	 * Set expired date.
	 *
	 * @param expiredDate the expired date
	 */
	public void setExpiredDate(LocalDate expiredDate){
		this.expiredDate=expiredDate;
	}
	private LocalDate expiredDate;

	/**
	 * Get csv string.
	 *
	 * @return the string
	 */
	@Basic
	public String getCsv(){
		return csv;
	}
	/**
	 * Set csv.
	 *
	 * @param csv the csv
	 */
	public void setCsv(String csv){
		this.csv=csv;
	}
	private String csv;

	/**
	 * Get owner person.
	 *
	 * @return the person
	 */
	@ManyToOne
	public Person getOwner(){
		return owner;
	}
	/**
	 * Set owner.
	 *
	 * @param owner the owner
	 */
	public void setOwner(Person owner){
		this.owner=owner;
	}
	private Person owner;


	/**
	 * Instantiates a new Credit card.
	 */
	protected CreditCard(){}
	private CreditCard(String number,LocalDate expiredDate,String csv,Person owner){
		this.number=number;
		this.expiredDate=expiredDate;
		this.csv=csv;
		this.owner=owner;
	}

	/**
	 * Add card to person credit card.
	 *
	 * @param owner       the owner
	 * @param number      the number
	 * @param expiredDate the expired date
	 * @param csv         the csv
	 * @return the credit card
	 * @throws Exception the exception
	 */
	public static CreditCard addCardToPerson(Person owner,String number,LocalDate expiredDate,String csv) throws Exception{
		if(checkNumbers(number)){
			throw new Exception("Card already added");
		}
		if(owner.checkPersonKind(PersonType.User)){
			addNumber(number);
			CreditCard creditCard=new CreditCard(number,expiredDate,csv,owner);
			owner.addCard(creditCard);
			return creditCard;
		}else{
			throw new Exception("Cant add card to this person");
		}
	}

	/**
	 * Is valid boolean.
	 *
	 * @return the boolean
	 */
	@Transient
	public boolean isValid(){
		return Period.between(LocalDate.from(getExpiredDate()),LocalDate.now()).isNegative();
	}

	@Override
	public String toString(){
		return "Models.CreditCard{"+
				"number='"+number+'\''+
				", expiredDate="+expiredDate+
				", owner="+owner.getLogin()+
				'}';
	}

}
