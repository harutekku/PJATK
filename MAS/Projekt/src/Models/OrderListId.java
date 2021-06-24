package Models;

import java.io.Serializable;
import java.util.Objects;

public class OrderListId implements Serializable{
	private Order order;
	public Order getOrder(){
		return order;
	}
	public void setOrder(Order order){
		this.order=order;
	}

	private OfferItem offerItem;
	public OfferItem getOfferItem(){
		return offerItem;
	}
	public void setOfferItem(OfferItem offerItem){
		this.offerItem=offerItem;
	}

	private OrderListId(){}
	public OrderListId(Order order,OfferItem offerItem){
		this.order=order;
		this.offerItem=offerItem;
	}

	@Override
	public boolean equals(Object o){
		if(this==o) return true;
		if(o==null||getClass()!=o.getClass()) return false;
		OrderListId orderId=(OrderListId)o;
		return Objects.equals(order,orderId.order)&&
				Objects.equals(offerItem,orderId.offerItem);
	}
	@Override
	public int hashCode(){
		return Objects.hash(order,offerItem);
	}
}
