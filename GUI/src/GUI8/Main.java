package GUI8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("calc.fxml"));
        Scene scene = new Scene(root, 400, 600);


        stage.setTitle("Kalkulator");

        stage.setScene(scene);

        stage.show();
    }

}