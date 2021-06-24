package GUI;

import Models.Local;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * The type Local list cell controller.
 */
public class LocalListCellController extends ListCell<Local>{
	@FXML
	private Label nameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label numberLabel;
	@FXML
	private Button orderButton;

	private FXMLLoader loader;
	private Local local;

	@Override
	protected void updateItem(Local local,boolean isEmpty){
		super.updateItem(local,isEmpty);
		this.local=local;

		if(isEmpty||local==null){
			setText(null);
			setGraphic(null);
		}else{
			if(loader==null){
				loader=new FXMLLoader(getClass().getResource("LocalListCell.fxml"));
				loader.setController(this);
				try{
					loader.load();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			nameLabel.setText(local.getOwner().getName());
			streetLabel.setText(local.getStreet());
			numberLabel.setText(String.valueOf(local.getNumber()));
			setText(null);
			setGraphic(loader.getRoot());
		}
	}

}
