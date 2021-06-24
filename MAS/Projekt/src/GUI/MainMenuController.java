package GUI;

import Models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenuController{
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	public void openLocalList(ActionEvent event) throws IOException{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("LocalList.fxml"));
		root=loader.load();

		//LocalListController localListController=loader.getController();
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene=new Scene(root);
		stage.setScene(scene);
		stage.show();


	}

	public static void returnToMainMenu(ActionEvent event) throws IOException{
		changeScene((Stage)((Node)event.getSource()).getScene().getWindow(),"MainMenu.fxml");
	}
	public static void returnToLocal(ActionEvent event) throws IOException{
		changeScene((Stage)((Node)event.getSource()).getScene().getWindow(),"LocalList.fxml");
	}

	public static <T> T changeScene(ActionEvent e,String uri) throws IOException{
		return changeScene((Stage)((Node)e.getSource()).getScene().getWindow(),uri);
	}

	public static <T> T changeScene(Stage stage,String uri) throws IOException{
		FXMLLoader loader=new FXMLLoader(MainMenuController.class.getResource(uri));
		Parent root=loader.load();
		T controller=loader.getController();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
		return controller;
	}


}
