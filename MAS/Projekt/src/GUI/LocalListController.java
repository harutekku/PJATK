package GUI;

import Controller.LocalController;
import Models.Local;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LocalListController implements Initializable{
	/*@FXML
	private ListView<String> listView;*/
	/*@FXML
	private Label label;*/
	@FXML
	private ListView<Local> localList;
	private Local local;

	List<Local> locals;


	@Override
	public void initialize(URL arg0,ResourceBundle arg1){
		LocalController localController=new LocalController();
		locals=localController.getAll();
		if(locals.isEmpty())return;
		localList.setCellFactory(localListView -> new LocalListCellController());
		localList.getItems().addAll(locals);

		localList.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					local = localList.getSelectionModel().getSelectedItem();
					try {
						OfferListController controller = MainMenuController.changeScene(
								(Stage)localList.getScene().getWindow(),"OfferList.fxml"
						);
						controller.setLocal(local);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		);
	}

	public void onBackToMenu(ActionEvent e) throws IOException {
		MainMenuController.returnToMainMenu(e);
	}


}
