package Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name="OrderList")
@IdClass(OrderListId.class)
public class OrderList implements Serializable{
	@Basic
	public int getCount(){
		return count;
	}
	public void setCount(int count){
		this.count=count;
	}
	private int count;

	@Id
	@ManyToOne
	public Order getOrder(){
		return order;
	}
	public void setOrder(Order order){
		this.order=order;
	}
	private Order order;

	@Id
	@ManyToOne
	public OfferItem getOfferItem(){
		return offerItem;
	}
	public void setOfferItem(OfferItem offerItem){
		this.offerItem=offerItem;
	}
	private OfferItem offerItem;

	protected OrderList(){}
	private OrderList(int count,Order order, OfferItem offerItem){
		this.count=count;
		this.order=order;
		this.offerItem=offerItem;
	}
	public static OrderList createOrderList(int count,Order order, OfferItem offerItem) throws Exception{
		OrderList orderList=new OrderList(count,order,offerItem);
		order.addOrderList(orderList);
		offerItem.addOrderList(orderList);
		return orderList;
	}
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
