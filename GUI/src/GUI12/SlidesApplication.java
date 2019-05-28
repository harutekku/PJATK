package GUI12;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidesApplication {

    private static SlidesApplication instance;
    private Map<String, Stage> stageMap;
    private int currentSlide;
    private List<Slide> slideData;

    static SlidesApplication getInstance() {
        if (instance == null) {
            instance = new SlidesApplication();
        }
        return instance;
    }

    void registerStage(String name, Stage stage) {
        stageMap.put(name, stage);
    }

    void loadScene(String stageName, Scene scene) {
        Stage stage = stageMap.get(stageName);
        stage.setScene(scene);
    }

    private SlidesApplication() {
        stageMap = new HashMap<>();
        slideData = new ArrayList<>();
        currentSlide = 0;
        initSlidesData();
    }
    private void initSlidesData() {
        slideData.add(new Slide("Podpowiedź dla prelegenta 0", "Slide0","Komentarz",new Background(new BackgroundImage(new Image("file:///GUI12/ping.jpg"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT))));
        slideData.add(new Slide("Podpowiedź dla prelegenta 1", "Slide1","Kocham babierza",new Background(new BackgroundImage(new Image("file:/kot.jpg"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT))));
        slideData.add(new Slide("Podpowiedź dla prelegenta 2", "Slide2","maselko",new Background(new BackgroundImage(new Image("file:/kot2.jpg"),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT))));
    }


    Scene renderScene() {
        Slide current = slideData.get(currentSlide);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Slide.fxml"));

        SlideController controller = new SlideController(current);
        loader.setController(controller);
        try {
            Pane pane = loader.load();
            return new Scene(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // @TODO
    // - Zmienić index obecnego slajdu
    // - Zwrócić scenę dla projektora slajdów
    Scene turnLeft() {
        if (currentSlide>0){
            currentSlide--;
            Slide current = slideData.get(currentSlide);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Slide.fxml"));

            SlideController controller = new SlideController(current);
            loader.setController(controller);
            try {
                Pane pane = loader.load();
                return new Scene(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // @TODO
    // Analogicznie dla turnLeft(), ale w przeciwną stronę
    Scene turnRight() {
        if (currentSlide<slideData.size()-1){
            currentSlide++;
            Slide current = slideData.get(currentSlide);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Slide.fxml"));

            SlideController controller = new SlideController(current);
            loader.setController(controller);
            try {
                Pane pane = loader.load();
                return new Scene(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // @TODO
    // Musimy zwracać informację o podpowiedzi dla prelegenta
    String currentMessage() {
        return slideData.get(currentSlide).getMessage();
    }

    void createSlideStage() {
        Stage stage = new Stage();
        stage.setTitle("SlideStage");
        stage.setScene(renderScene());
        stage.show();
        registerStage("SlideStage", stage);
    }
}
