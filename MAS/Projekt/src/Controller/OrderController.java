package Controller;

import DAO.DAOGeneric;
import Models.Order;

import java.util.List;

public class OrderController{
	private DAOGeneric<Order,Long> OrderDAO=new DAOGeneric<>(Order.class);
	public List<Order> getAll(){
		return OrderDAO.getAll();
	}
	public Order get(long id){
		return OrderDAO.get(id);
	}
	public Order update(Order order){
		return OrderDAO.update(order);
	}
	public void add(Order order){
		OrderDAO.add(order);
	}
	public void remove(Order order){
		OrderDAO.remove(order);
	}
}
