package GUI11;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MainController {

    @FXML
    private VBox listOfElements;

    @FXML
    private TextField nickField;

    @FXML
    private void addNick(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cell.fxml"));
        Cell controller = new Cell(nickField.getText());
        loader.setController(controller);
        Pane pane = null;
        try {
            pane = loader.load();
            listOfElements.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}