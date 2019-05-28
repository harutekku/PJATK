package GUI12;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class MainController {

    private SlidesApplication slides;

    @FXML
    private Label text;

    @FXML
    public void initialize() {
        slides = SlidesApplication.getInstance();
        slides.createSlideStage();
        text.setText(slides.currentMessage());
    }

    @FXML
    public void slideLeft() {
        Scene scene = slides.turnLeft();
        slides.loadScene("SlideStage", scene);
        text.setText(slides.currentMessage());
    }

    @FXML
    public void slideRight() {
        Scene scene = slides.turnRight();
        slides.loadScene("SlideStage", scene);
        text.setText(slides.currentMessage());
    }

}
