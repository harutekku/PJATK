import java.io.Serializable;
import java.util.Objects;

public class ReviewId implements Serializable{
	private Person reviewer;
	public Person getReviewer(){
		return reviewer;
	}
	public void setReviewer(Person reviewer){
		this.reviewer=reviewer;
	}

	private Local local;
	public Local getLocal(){
		return local;
	}
	public void setLocal(Local local){
		this.local=local;
	}


	private ReviewId(){}
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
