package GUI15projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultCellController implements Comparable{
    String nick;
    long time;
    @FXML
    Label nickTime;
    @FXML
    Label levelSwap;
    byte level;
    boolean allSwap;
    @FXML
    void initialize(){
        nickTime.setText(nick+": "+((time)/10)/100.0);
        levelSwap.setText(((level==0)?"Łatwy":(level==1)?"Średni":"Trudny")+" "+(allSwap?"z zamianą":""));
    }
    ResultCellController(String nick, long time, byte level, boolean allSwap){
        this.nick=nick;
        this.time=time;
        this.level=level;
        this.allSwap=allSwap;
    }
    String getNick(){
        return nick;
    }
    long getTime(){
        return time;
    }
    byte getLevel(){
        return level;
    }
    boolean getAllSwap() {
        return allSwap;
    }

    @Override
    public int compareTo(Object o) {
        return (int) (time-((ResultCellController)o).time);
    }
}
