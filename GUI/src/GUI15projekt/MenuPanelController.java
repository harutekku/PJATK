package GUI15projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MenuPanelController {
    @FXML
    private TextField nickField;
    @FXML
    private ObservableList<String> gameMode = FXCollections.observableArrayList("Łatwy", "Średni", "Trudny");
    @FXML
    private ChoiceBox<String> gameModeBox;
    @FXML
    private FileChooser fileChooser;
    @FXML
    private CheckBox allSwapBox;

    private Image image;

    private Stage stage;

    @FXML
    private void initialize() {
        gameModeBox.setItems(gameMode);
        gameModeBox.setValue(gameMode.get(0));
        fileChooser = new FileChooser();
    }

    @FXML
    private void selectImage() {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            image = new Image(file.toURI().toString());
            if (image.isError()) {
                image = null;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Musisz wybrać zdjęcie");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void startGame(ActionEvent event) {
        if (nickField.getText().length() > 0) {
            if (image == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Brak zdjęcia");
                alert.setHeaderText(null);
                alert.setContentText("Nie wybrano zdjęcia, kontynuować na domyślnym?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.CANCEL || result.get() == ButtonType.CLOSE) return;
                image = new Image(String.valueOf(new File("GUI15projekt/kot.jpg")));
            }
            try {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePanel.fxml"));
                Parent root = fxmlLoader.load();
                GamePanelController gamePanelController = fxmlLoader.getController();
                gamePanelController.settings(allSwapBox.isSelected(), nickField.getText(), (byte) (gameMode.indexOf(gameModeBox.getValue())),stage);
                gamePanelController.cropImages(image);
                Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
                scene.setOnKeyPressed(gamePanelController::keyClicked);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nick");
            alert.setHeaderText(null);
            alert.setContentText("Musisz podać nick");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            nickField.requestFocus();
        }
    }

    @FXML
    private void showResults(ActionEvent event) {
        try {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ResultsPanel.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 200, 600);
            stage.setScene(scene);
            stage.setMinWidth(200);
            stage.setMinHeight(400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
