package GUI15projekt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;

public class ResultsPanelController {
    @FXML
    VBox resultVBox;

    @FXML
    void initialize(){
        FileOperations fo=new FileOperations();
        ResultCellController[] cells=fo.readCells();
        for(int i=0;i<cells.length;i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultCell.fxml"));
            loader.setController(cells[i]);
            try {
                Pane pane = loader.load();
                resultVBox.getChildren().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
