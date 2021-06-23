package Models;

import javax.persistence.*;

@Entity(name="Review")
@IdClass(ReviewId.class)
public class Review{
	@Basic
	public int getRate(){
		return rate;
	}
	private void setRate(int rate){
		this.rate=rate;
	}
	private int rate;

	@Basic
	public String getDescription(){
		return description;
	}
	private void setDescription(String description){
		this.description=description;
	}
	private String description;

	@Id
	@ManyToOne
	public Person getReviewer(){
		return reviewer;
	}
	public void setReviewer(Person reviewer){
		this.reviewer=reviewer;
	}
	private Person reviewer;

	@Id
	@ManyToOne
	public Local getLocal(){
		return local;
	}
	public void setLocal(Local local){
		this.local=local;
	}
	private Local local;

	protected Review(){}
	private Review(int rate,String description,Person reviewer,Local local){
		this.rate=rate;
		this.description=description;
		this.reviewer=reviewer;
		this.local=local;
	}
}
