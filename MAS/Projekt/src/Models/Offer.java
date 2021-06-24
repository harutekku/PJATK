package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Offer.
 */
@Entity(name="Offer")
public class Offer{
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
	 * Get creation date local date time.
	 *
	 * @return the local date time
	 */
	@Basic
	public LocalDateTime getCreationDate(){
		return creationDate;
	}
	private void setCreationDate(LocalDateTime creationDate){
		this.creationDate=creationDate;
	}
	private LocalDateTime creationDate;

	/**
	 * Get validity start local date time.
	 *
	 * @return the local date time
	 */
	@Basic
	public LocalDateTime getValidityStart(){
		return validityStart;
	}
	private void setValidityStart(LocalDateTime validityStart){
		this.validityStart=validityStart;
	}
	private LocalDateTime validityStart;

	/**
	 * Get validity end local date time.
	 *
	 * @return the local date time
	 */
	@Basic
	public LocalDateTime getValidityEnd(){
		return validityEnd;
	}
	private void setValidityEnd(LocalDateTime validityEnd){
		this.validityEnd=validityEnd;
	}
	private LocalDateTime validityEnd;

	/**
	 * Get author person.
	 *
	 * @return the person
	 */
	@ManyToOne
	public Person getAuthor(){
		return author;
	}
	private void setAuthor(Person author){
		this.author=author;
	}
	private Person author;

	/**
	 * Get local local.
	 *
	 * @return the local
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	public Local getLocal(){
		return local;
	}
	private void setLocal(Local local){
		this.local=local;
	}
	private Local local;

	/**
	 * Get offer items list.
	 *
	 * @return the list
	 */
	@OneToMany(mappedBy="offer", cascade=CascadeType.REMOVE, orphanRemoval=true, fetch=FetchType.EAGER)
	public List<OfferItem> getOfferItems(){
		return offerItems;
	}
	private void setOfferItems(List<OfferItem> offerItems){
		this.offerItems=offerItems;
	}
	/**
	 * Add offer item.
	 *
	 * @param offerItem the offer item
	 * @throws Exception the exception
	 */
	public void addOfferItem(OfferItem offerItem) throws Exception{
		if(offerItem.getOffer().equals(this)){
			offerItems.add(offerItem);
		}else{
			throw new Exception("Models.Offer item is not created for this offer");
		}
	}
	private List<OfferItem> offerItems=new ArrayList<>();

	/**
	 * Instantiates a new Offer.
	 */
	protected Offer(){}
	/**
	 * Instantiates a new Offer.
	 *
	 * @param creationDate  the creation date
	 * @param validityStart the validity start
	 * @param validityEnd   the validity end
	 * @param author        the author
	 * @param local         the local
	 */
	public Offer(LocalDateTime creationDate,LocalDateTime validityStart,LocalDateTime validityEnd,Person author, Local local){
		this.creationDate=creationDate;
		this.validityStart=validityStart;
		this.validityEnd=validityEnd;
		this.author=author;
		this.local=local;
	}

}

