package GUI15projekt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ResultsPanelController {
    @FXML
    VBox resultVBox;

    @FXML
    void initialize(){

    }
    void addCell(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultCell.fxml"));
        ResultCellController controller = new ResultCellController("adam",100);
        loader.setController(controller);
        try {
            Pane pane = loader.load();
            resultVBox.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
