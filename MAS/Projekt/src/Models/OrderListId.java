package Models;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Order list id.
 */
public class OrderListId implements Serializable{
	private Order order;
	/**
	 * Get order order.
	 *
	 * @return the order
	 */
	public Order getOrder(){
		return order;
	}
	/**
	 * Set order.
	 *
	 * @param order the order
	 */
	public void setOrder(Order order){
		this.order=order;
	}

	private OfferItem offerItem;
	/**
	 * Get offer item offer item.
	 *
	 * @return the offer item
	 */
	public OfferItem getOfferItem(){
		return offerItem;
	}
	/**
	 * Set offer item.
	 *
	 * @param offerItem the offer item
	 */
	public void setOfferItem(OfferItem offerItem){
		this.offerItem=offerItem;
	}

	private OrderListId(){}
	/**
	 * Instantiates a new Order list id.
	 *
	 * @param order     the order
	 * @param offerItem the offer item
	 */
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
