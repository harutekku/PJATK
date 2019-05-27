package GUI15projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MenuPanel.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Puzzle");
        stage.setScene(scene);
        stage.show();
    }

}
