package GUI15projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MenuPanelController {

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

    private Image image=new Image("file:/C:/Users/Kubbit/Desktop/kot.png");



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
    private void startGame(){

    }
    @FXML
    private void showResults(){

    }
}
