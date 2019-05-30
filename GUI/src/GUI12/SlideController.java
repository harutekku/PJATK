package GUI12;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;


public class SlideController {

    @FXML
    private Label text;

    @FXML
    private TextArea comment;

    @FXML
    private VBox vbox;

    private Slide slide;

    public SlideController(Slide slide) {
        this.slide = slide;
    }

    @FXML
    public void initialize() {
        text.setText(slide.getTitle());
        comment.setText(slide.getComment());
        vbox.setBackground(slide.getBackground());
    }

}
