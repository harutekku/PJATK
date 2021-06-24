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


/**
 * The type Main menu controller.
 */
public class MainMenuController{
	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * Open local list.
	 *
	 * @param event the event
	 * @throws IOException the io exception
	 */
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

	/**
	 * Return to main menu.
	 *
	 * @param event the event
	 * @throws IOException the io exception
	 */
	public static void returnToMainMenu(ActionEvent event) throws IOException{
		changeScene((Stage)((Node)event.getSource()).getScene().getWindow(),"MainMenu.fxml");
	}
	/**
	 * Return to local.
	 *
	 * @param event the event
	 * @throws IOException the io exception
	 */
	public static void returnToLocal(ActionEvent event) throws IOException{
		changeScene((Stage)((Node)event.getSource()).getScene().getWindow(),"LocalList.fxml");
	}

	/**
	 * Change scene t.
	 *
	 * @param <T> the type parameter
	 * @param e   the e
	 * @param uri the uri
	 * @return the t
	 * @throws IOException the io exception
	 */
	public static <T> T changeScene(ActionEvent e,String uri) throws IOException{
		return changeScene((Stage)((Node)e.getSource()).getScene().getWindow(),uri);
	}

	/**
	 * Change scene t.
	 *
	 * @param <T>   the type parameter
	 * @param stage the stage
	 * @param uri   the uri
	 * @return the t
	 * @throws IOException the io exception
	 */
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
