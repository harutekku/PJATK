package GUI;

import Models.Local;
import Models.Offer;
import Models.OfferItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;


import java.io.IOException;
import java.math.BigDecimal;

public class OfferListCellController extends ListCell<OfferItem>{
	@FXML
	private Label nameLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private Label countLabel;
	@FXML
	private Button addButton;
	@FXML
	private Button removeButton;

	private FXMLLoader loader;
	private OfferItem offerItem;
	private OfferListController offerListController;
	public OfferListCellController(OfferListController offerListController){
		this.offerListController=offerListController;
	}

	@Override
	protected void updateItem(OfferItem offerItem,boolean isEmpty){
		super.updateItem(offerItem,isEmpty);
		this.offerItem=offerItem;

		if(isEmpty||offerItem==null){
			setText(null);
			setGraphic(null);
		}else{
			if(loader==null){
				loader=new FXMLLoader(getClass().getResource("OfferListCell.fxml"));
				loader.setController(this);
				try{
					loader.load();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			nameLabel.setText(offerItem.getName());
			priceLabel.setText(offerItem.getPrice().toString());
			countLabel.setText("0");
			setText(null);
			setGraphic(loader.getRoot());
		}
	}

	public void increment(ActionEvent e) throws IOException{
		countLabel.setText(String.valueOf(Integer.parseInt(countLabel.getText())+1));
		offerListController.addItem(offerItem);
		/*int count=Integer.parseInt(countLabel.getText());
		BigDecimal bigDecimal=new BigDecimal(0);
		for(int i=0;i<count;i++){
			bigDecimal.add(new BigDecimal(Float.valueOf(priceLabel.toString())));
		}
		offerListController.updatePrice();*/
	}
	public void decrement(ActionEvent e) throws IOException{
		if(Integer.parseInt(countLabel.getText())>0){
			countLabel.setText(String.valueOf(Integer.parseInt(countLabel.getText())-1));
			offerListController.removeItem(offerItem);
		}
	}
	public int getCount(){
		return Integer.parseInt(countLabel.getText());
	}

}
