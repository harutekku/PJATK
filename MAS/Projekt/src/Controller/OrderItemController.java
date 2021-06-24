package Controller;

import DAO.DAOGeneric;
import Models.Order;
import Models.OrderList;
import Models.OrderListId;

import java.util.List;

public class OrderItemController{
	private DAOGeneric<OrderList,OrderListId> OrderDAO=new DAOGeneric<>(OrderList.class);
	public List<OrderList> getAll(){
		return OrderDAO.getAll();
	}
	public OrderList get(long id){
		return OrderDAO.get(id);
	}
	public OrderList update(OrderList orderList){
		return OrderDAO.update(orderList);
	}
	public void add(OrderList orderList){
		OrderDAO.add(orderList);
	}
	public void remove(OrderList orderList){
		OrderDAO.remove(orderList);
	}
}
