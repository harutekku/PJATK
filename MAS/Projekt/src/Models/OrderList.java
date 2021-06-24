package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * The type Order list.
 */
@Entity(name="OrderList")
@IdClass(OrderListId.class)
public class OrderList implements Serializable{
	/**
	 * Get count int.
	 *
	 * @return the int
	 */
	@Basic
	public int getCount(){
		return count;
	}
	/**
	 * Set count.
	 *
	 * @param count the count
	 */
	public void setCount(int count){
		this.count=count;
	}
	private int count;

	/**
	 * Get order order.
	 *
	 * @return the order
	 */
	@Id
	@ManyToOne
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
	private Order order;

	/**
	 * Get offer item offer item.
	 *
	 * @return the offer item
	 */
	@Id
	@ManyToOne
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
	private OfferItem offerItem;

	/**
	 * Instantiates a new Order list.
	 */
	protected OrderList(){}
	private OrderList(int count,Order order, OfferItem offerItem){
		this.count=count;
		this.order=order;
		this.offerItem=offerItem;
	}
	/**
	 * Create order list order list.
	 *
	 * @param count     the count
	 * @param order     the order
	 * @param offerItem the offer item
	 * @return the order list
	 * @throws Exception the exception
	 */
	public static OrderList createOrderList(int count,Order order, OfferItem offerItem) throws Exception{
		OrderList orderList=new OrderList(count,order,offerItem);
		order.addOrderList(orderList);
		offerItem.addOrderList(orderList);
		return orderList;
	}
	/**
	 * Create order list order list.
	 *
	 * @param order     the order
	 * @param offerItem the offer item
	 * @return the order list
	 * @throws Exception the exception
	 */
	public static OrderList createOrderList(Order order, OfferItem offerItem) throws Exception{
		OrderList orderList=new OrderList(1,order,offerItem);
		//order.addOrderList(orderList);
		//offerItem.addOrderList(orderList);
		return orderList;
	}

	@Override
	public boolean equals(Object o){
		if(this==o) return true;
		if(o==null||getClass()!=o.getClass()) return false;
		OrderList orderId=(OrderList)o;
		return Objects.equals(order,orderId.order)&&
				Objects.equals(offerItem,orderId.offerItem);
	}
	@Override
	public int hashCode(){
		return Objects.hash(order,offerItem);
	}
}
