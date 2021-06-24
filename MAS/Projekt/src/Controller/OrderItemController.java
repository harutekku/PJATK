package Controller;

import DAO.DAOGeneric;
import Models.Order;
import Models.OrderList;
import Models.OrderListId;

import java.util.List;

/**
 * The type Order item controller.
 */
public class OrderItemController{
	private DAOGeneric<OrderList,OrderListId> OrderDAO=new DAOGeneric<>(OrderList.class);
	/**
	 * Get all list.
	 *
	 * @return the list
	 */
	public List<OrderList> getAll(){
		return OrderDAO.getAll();
	}
	/**
	 * Get order list.
	 *
	 * @param id the id
	 * @return the order list
	 */
	public OrderList get(long id){
		return OrderDAO.get(id);
	}
	/**
	 * Update order list.
	 *
	 * @param orderList the order list
	 * @return the order list
	 */
	public OrderList update(OrderList orderList){
		return OrderDAO.update(orderList);
	}
	/**
	 * Add.
	 *
	 * @param orderList the order list
	 */
	public void add(OrderList orderList){
		OrderDAO.add(orderList);
	}
	/**
	 * Remove.
	 *
	 * @param orderList the order list
	 */
	public void remove(OrderList orderList){
		OrderDAO.remove(orderList);
	}
}
