import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.TreeSet;

@Entity(name="CreditCard")
public class CreditCard{
	private static Set<String> numbers=new TreeSet<>();
	public static boolean checkNumbers(String number){
		return numbers.contains(number);
	}
	public static void addNumber(String number){
		numbers.add(number);
	}

	@Id
	@Basic
	public String getNumber(){
		return number;
	}
	public void setNumber(String number){
		this.number=number;
	}
	private String number;

	@Basic
	public LocalDate getExpiredDate(){
		return expiredDate;
	}
	public void setExpiredDate(LocalDate expiredDate){
		this.expiredDate=expiredDate;
	}
	private LocalDate expiredDate;

	@Basic
	public String getCsv(){
		return csv;
	}
	public void setCsv(String csv){
		this.csv=csv;
	}
	private String csv;

	@ManyToOne
	public Person getOwner(){
		return owner;
	}
	public void setOwner(Person owner){
		this.owner=owner;
	}
	private Person owner;


	protected CreditCard(){}
	private CreditCard(String number,LocalDate expiredDate,String csv,Person owner){
		this.number=number;
		this.expiredDate=expiredDate;
		this.csv=csv;
		this.owner=owner;
	}

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

	@Transient
	public boolean isValid(){
		return Period.between(LocalDate.from(getExpiredDate()),LocalDate.now()).isNegative();
	}

	@Override
	public String toString(){
		return "CreditCard{"+
				"number='"+number+'\''+
				", expiredDate="+expiredDate+
				", owner="+owner.getLogin()+
				'}';
	}

}
