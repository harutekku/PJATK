package GUI13;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class MainViewController {
    @FXML
    AnchorPane anchor;
    @FXML
    Slider aX, bX, aY, bY;
    @FXML
    Line line;

    public void initialize() {
        line.startXProperty().bind(aX.valueProperty());
        line.startYProperty().bind(aY.valueProperty());
        line.endXProperty().bind(bX.valueProperty());
        line.endYProperty().bind(bY.valueProperty());
    }
}