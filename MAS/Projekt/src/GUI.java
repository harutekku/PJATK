import Configuration.HibernateConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;

public class GUI extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		try{
			java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
			HibernateConfig.StartHibernateConfiguration();
			//BorderPane root=new BorderPane();
			Parent root =FXMLLoader.load(getClass().getResource("GUI/MainMenu.fxml"));
			Scene scene=new Scene(root);
			//scene.getStylesheets()
			primaryStage.setTitle("Zamawianko");
			primaryStage.setScene(scene);
			primaryStage.show();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		launch(args);
	}

}
