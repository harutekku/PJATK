package Models;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Review id.
 */
public class ReviewId implements Serializable{
	private Person reviewer;
	/**
	 * Get reviewer person.
	 *
	 * @return the person
	 */
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

	private Local local;
	/**
	 * Get local local.
	 *
	 * @return the local
	 */
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


	private ReviewId(){}
	/**
	 * Instantiates a new Review id.
	 *
	 * @param reviewer the reviewer
	 * @param local    the local
	 */
	public ReviewId(Person reviewer,Local local){
		this.reviewer=reviewer;
		this.local=local;
	}

	@Override
	public boolean equals(Object o){
		if(this==o) return true;
		if(o==null||getClass()!=o.getClass()) return false;
		ReviewId reviewId=(ReviewId)o;
		return Objects.equals(reviewer,reviewId.reviewer)&&
				Objects.equals(local,reviewId.local);
	}
	@Override
	public int hashCode(){
		return Objects.hash(reviewer,local);
	}

}
