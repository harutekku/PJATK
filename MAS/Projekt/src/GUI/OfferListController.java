package GUI;

import Controller.LocalController;
import Controller.OfferItemController;
import Controller.OrderController;
import Controller.PersonController;
import Models.Local;
import Models.OfferItem;
import Models.Order;
import Models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OfferListController implements Initializable{
	@FXML
	private Label priceLabel;
	@FXML
	private ListView<OfferItem> offerItemList;
	private List<OfferItem> offerItems;
	private Local local;
	private OfferItem offerItem;
	private List<OfferItem> choosedItems;
	private BigDecimal bigDecimal;

	@Override
	public void initialize(URL arg0,ResourceBundle arg1){
		choosedItems=new ArrayList<>();
	}

	public void setLocal(Local local){
		this.local=local;
		offerItems=local.getActualOfferItems();
		if(offerItems.isEmpty()) return;

		offerItemList.setCellFactory(offerItemListView->new OfferListCellController(this));
		offerItemList.getItems().addAll(offerItems);
	}
	public void onBackToLocal(ActionEvent e) throws IOException{
		MainMenuController.returnToLocal(e);
	}
	public void onBackToMenu(ActionEvent e) throws IOException{
		MainMenuController.returnToMainMenu(e);
	}
	public void confirmOrder(ActionEvent event) throws Exception{
		//Person user=new PersonController().get("badziak");
		if(choosedItems.size()<1){
			return;
		}
		Person user=new PersonController().getAll().get(1);
		Order order=Order.createOrder(user,local,bigDecimal,choosedItems);
		new OrderController().add(order);
		SummaryMenuController controller=MainMenuController.changeScene(
				(Stage)priceLabel.getScene().getWindow(),"SummaryMenu.fxml"
		);
		controller.setData(order);
	}

	public void updatePrice(){
		BigDecimal bigDecimal=new BigDecimal(0).setScale(2);
		for(OfferItem offerItem: choosedItems){
			bigDecimal=bigDecimal.add(offerItem.getPrice());
		}
		priceLabel.setText(bigDecimal.setScale(2).toString());
		this.bigDecimal=bigDecimal;
		//BigDecimal bd=new BigDecimal(0);
		//offerItemList.getCellFactory().

	}
	public void addItem(OfferItem offerItem){
		choosedItems.add(offerItem);
		updatePrice();
		//System.out.println(choosedItems.size());
	}
	public void removeItem(OfferItem offerItem){
		choosedItems.remove(offerItem);
		updatePrice();
	}

}
