package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

	/*@ManyToMany(mappedBy="offerItemsList")
	public Set<Order> getOrders(){
		return orders;
	}
	private void setOrders(Set<Order> orders){
		this.orders=orders;
	}
	private Set<Order> orders=new HashSet<Order>();*/

	@OneToMany(mappedBy="offerItem", cascade=CascadeType.REMOVE, orphanRemoval=true)
	public List<OrderList> getOrderList(){
		return orderList;
	}
	private void setOrderList(List<OrderList> orderList){
		this.orderList=orderList;
	}
	public void addOrderList(OrderList orderList) throws Exception{
		if(orderList.getOrder().equals(this)){
			this.orderList.add(orderList);
		}else{
			throw new Exception("This orderList is not created for this order");
		}
	}
	private List<OrderList> orderList=new ArrayList<>();

	protected OfferItem(){}
	public OfferItem(String name,BigDecimal price,Offer offer){
		this.name=name;
		this.price=price;
		this.offer=offer;
	}

}
