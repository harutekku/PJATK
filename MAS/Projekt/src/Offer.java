import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Offer")
public class Offer{
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
	public LocalDateTime getCreationDate(){
		return creationDate;
	}
	private void setCreationDate(LocalDateTime creationDate){
		this.creationDate=creationDate;
	}
	private LocalDateTime creationDate;

	@Basic
	public LocalDateTime getValidityStart(){
		return validityStart;
	}
	private void setValidityStart(LocalDateTime validityStart){
		this.validityStart=validityStart;
	}
	private LocalDateTime validityStart;

	@Basic
	public LocalDateTime getValidityEnd(){
		return validityEnd;
	}
	private void setValidityEnd(LocalDateTime validityEnd){
		this.validityEnd=validityEnd;
	}
	private LocalDateTime validityEnd;

	@ManyToOne
	public Person getAuthor(){
		return author;
	}
	private void setAuthor(Person author){
		this.author=author;
	}
	private Person author;

	@ManyToOne
	public Local getLocal(){
		return local;
	}
	private void setLocal(Local local){
		this.local=local;
	}
	private Local local;

}

