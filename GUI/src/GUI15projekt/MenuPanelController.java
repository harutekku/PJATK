package GUI15projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class MenuPanelController {
    @FXML
    private TextField nickField;
    @FXML
    private ObservableList<String> gameMode= FXCollections.observableArrayList("Łatwy","Średni","Trudny");
    @FXML
    private ChoiceBox<String> gameModeBox;
    @FXML
    private Button imageSelect;
    @FXML
    private Button play;
    @FXML
    private Button results;
    @FXML
    private FileChooser fileChooser;
    @FXML
    private CheckBox allSwapBox;

    private Image image=new Image("file:/C:/Users/Kubbit/Desktop/kot.png");

    Stage stage;

    @FXML
    private void initialize(){
        gameModeBox.setItems(gameMode);
        gameModeBox.setValue(gameMode.get(0));
        fileChooser=new FileChooser();
    }
    @FXML
    private void selectImage(){
        Stage stage=new Stage();
        File file=fileChooser.showOpenDialog(stage);
        if(file!=null){
            image=new Image(file.toURI().toString());
            System.out.println(file.toURI().toString());
        }
        System.out.println(image.getHeight());
    }
    @FXML
    private void startGame(ActionEvent event){
        try {
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("GamePanel.fxml"));
            Parent root=fxmlLoader.load();
            GamePanelController gamePanelController=fxmlLoader.getController();
            gamePanelController.cropImages(image,gameModeBox.getValue());
            //gamePanelController.shuffle();
            gamePanelController.settings(allSwapBox.isSelected(),nickField.getText());
            gamePanelController.setStage(stage);
            Scene scene = new Scene(root,stage.getWidth(),stage.getHeight());
            scene.setOnKeyPressed(gamePanelController::keyClicked);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void showResults(){

    }
}
