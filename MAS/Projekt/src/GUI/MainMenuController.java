package GUI;

import Controller.LocalController;
import Models.Local;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class MainMenuController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	public void openLocalList(ActionEvent event) throws IOException{
		LocalController localController=new LocalController();
		List<Local> localList=localController.getAll();
		for(Local local: localList){
			System.out.println(local.getOwner().getName());
		}

		FXMLLoader loader=new FXMLLoader(getClass().getResource("LocalList.fxml"));
		root = loader.load();
		LocalListController localListController=loader.getController();
		//localListController.setList(localList);
		/*root=FXMLLoader.load(getClass().getResource("LocalList.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();*/

	}


}
