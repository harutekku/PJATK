package GUI;

import Models.OfferItem;
import Models.Order;
import Models.OrderList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SummaryMenuController implements Initializable{
	@FXML
	private ListView<String> itemList;

	private Order order;

	@Override
	public void initialize(URL location,ResourceBundle resources){

	}

	public void setData(Order order){
		this.order=order;
		List<OrderList> list=order.getOrderList();
		List<String> representativeStr=new ArrayList<>();
		for(OrderList orderList: list){
			String str=orderList.getOfferItem().getName()+" za "+orderList.getOfferItem().getPrice()+" zł w ilości "+orderList.getCount();
			representativeStr.add(str);
		}
		itemList.getItems().addAll(representativeStr);
	}

	public void onBackToLocals(ActionEvent event) throws IOException{
		MainMenuController.returnToLocal(event);
	}

}
