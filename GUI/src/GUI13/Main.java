package GUI13;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SliderView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Slidery");
        stage.setScene(scene);
        stage.show();
    }

}

