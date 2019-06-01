package GUI15projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultCellController {
    String nick;
    long time;
    @FXML
    Label label;
    @FXML
    void initialize(){
        label.setText(nick+": "+time);
    }
    ResultCellController(String nick, long time){
        this.nick=nick;
        this.time=time;
    }
}
