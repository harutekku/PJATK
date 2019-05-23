package GUI11;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Cell {
    private String name;
    private String password;

    public Cell(String name) {
        this.name = name;
        int len= (int) (Math.random()*6+4);
        this.password="";
        for(int i=0;i<len;i++){
            password+=(char)(Math.random()*26+'A');
        }
    }

    @FXML
    private Text text;

    @FXML
    public void show() {
        if(password.equals(text.getText())){
            text.setText(name+"");
        }
        else text.setText(password + "");
    }
    @FXML
    private void initialize(){
        text.setText(name+"");
    }

}