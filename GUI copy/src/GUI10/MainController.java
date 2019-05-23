package GUI10;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController extends Application {
    @FXML
    Button btn;

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Window.fxml"));
        Scene scene = new Scene(root, 200, 200);
        stage.setTitle("Klik");
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(){
        btn.setText("0");
    }
    @FXML
    private void onButtonClick() {
        int tmp=Integer.parseInt(btn.getText());
        btn.setText(++tmp +"");
    }
}
