package Models;

import javax.persistence.*;

/**
 * The type Review.
 */
@Entity(name="Review")
@IdClass(ReviewId.class)
public class Review{
	/**
	 * Get rate int.
	 *
	 * @return the int
	 */
	@Basic
	public int getRate(){
		return rate;
	}
	private void setRate(int rate){
		this.rate=rate;
	}
	private int rate;

	/**
	 * Get description string.
	 *
	 * @return the string
	 */
	@Basic
	public String getDescription(){
		return description;
	}
	private void setDescription(String description){
		this.description=description;
	}
	private String description;

	/**
	 * Get reviewer person.
	 *
	 * @return the person
	 */
	@Id
	@ManyToOne
	public Person getReviewer(){
		return reviewer;
	}
	/**
	 * Set reviewer.
	 *
	 * @param reviewer the reviewer
	 */
	public void setReviewer(Person reviewer){
		this.reviewer=reviewer;
	}
	private Person reviewer;

	/**
	 * Get local local.
	 *
	 * @return the local
	 */
	@Id
	@ManyToOne
	public Local getLocal(){
		return local;
	}
	/**
	 * Set local.
	 *
	 * @param local the local
	 */
	public void setLocal(Local local){
		this.local=local;
	}
	private Local local;

	/**
	 * Instantiates a new Review.
	 */
	protected Review(){}
	private Review(int rate,String description,Person reviewer,Local local){
		this.rate=rate;
		this.description=description;
		this.reviewer=reviewer;
		this.local=local;
	}
}
