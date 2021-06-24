package Controller;

import DAO.DAOGeneric;
import Models.Order;

import java.util.List;

/**
 * The type Order controller.
 */
public class OrderController{
	private DAOGeneric<Order,Long> OrderDAO=new DAOGeneric<>(Order.class);
	/**
	 * Get all list.
	 *
	 * @return the list
	 */
	public List<Order> getAll(){
		return OrderDAO.getAll();
	}
	/**
	 * Get order.
	 *
	 * @param id the id
	 * @return the order
	 */
	public Order get(long id){
		return OrderDAO.get(id);
	}
	/**
	 * Update order.
	 *
	 * @param order the order
	 * @return the order
	 */
	public Order update(Order order){
		return OrderDAO.update(order);
	}
	/**
	 * Add.
	 *
	 * @param order the order
	 */
	public void add(Order order){
		OrderDAO.add(order);
	}
	/**
	 * Remove.
	 *
	 * @param order the order
	 */
	public void remove(Order order){
		OrderDAO.remove(order);
	}
}
