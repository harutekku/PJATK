package GUI;

import Controller.LocalController;
import Models.Local;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LocalListController implements Initializable{
	@FXML
	private ListView<String> listView;
	@FXML
	private Label label;
	List<Local> locals;
	String[] localsString;
	Local local;

	@Override
	public void initialize(URL arg0,ResourceBundle arg1){
		LocalController localController=new LocalController();
		locals=localController.getAll();
		localsString=new String[locals.size()];
		for(int i=0;i<locals.size();i++){
			localsString[i]=locals.get(i).getOwner().getName()+" na ulicy "+locals.get(i).getStreet();
		}
		listView.getItems().addAll(localsString);
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable,Number oldValue,Number newValue){
				local=locals.get(listView.getSelectionModel().getSelectedIndex());
			}
		});
	}


}
