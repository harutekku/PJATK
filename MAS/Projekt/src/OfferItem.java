import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name="OfferItem")
public class OfferItem{
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
	public String getName(){
		return name;
	}
	private void setName(String name){
		this.name=name;
	}
	private String name;

	@Basic
	public BigDecimal getPrice(){
		return price;
	}
	private void setPrice(BigDecimal price){
		this.price=price;
	}
	private BigDecimal price;

	@ManyToOne
	public Offer getOffer(){return offer;}
	private void setOffer(Offer offer){
		this.offer=offer;
	}
	private Offer offer;

	@ManyToMany(mappedBy="orderList")
	public Set<Order> getOrders(){
		return orders;
	}
	private void setOrders(Set<Order> orders){
		this.orders=orders;
	}
	private Set<Order> orders=new HashSet<Order>();

	protected OfferItem(){}
	private OfferItem(String name,BigDecimal price){
		this.name=name;
		this.price=price;
	}
	public static OfferItem createOfferItem(String name,BigDecimal price){
		OfferItem offerItem=new OfferItem(name,price);
		return offerItem;
	}

}
