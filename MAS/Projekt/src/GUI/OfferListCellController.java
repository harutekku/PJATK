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

/**
 * The type Offer list cell controller.
 */
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
	/**
	 * Instantiates a new Offer list cell controller.
	 *
	 * @param offerListController the offer list controller
	 */
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

	/**
	 * Increment.
	 *
	 * @param e the e
	 * @throws IOException the io exception
	 */
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
	/**
	 * Decrement.
	 *
	 * @param e the e
	 * @throws IOException the io exception
	 */
	public void decrement(ActionEvent e) throws IOException{
		if(Integer.parseInt(countLabel.getText())>0){
			countLabel.setText(String.valueOf(Integer.parseInt(countLabel.getText())-1));
			offerListController.removeItem(offerItem);
		}
	}
	/**
	 * Get count int.
	 *
	 * @return the int
	 */
	public int getCount(){
		return Integer.parseInt(countLabel.getText());
	}

}
