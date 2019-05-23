package GUI9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception {
        // Ładujemy plik zasobu, który reprezentuje nasze okno
        Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));

        // Przyjmijmy, że Scene to pewne przybliżenie JFrame ze Swinga
        // Scene w JavaFX odpowiada za treść
        // Podajemy treść (root), szerokość (450) i wysokość (275)
        Scene scene = new Scene(root, 450, 275);

        // Ustawiamy tytuł głównego okna
        stage.setTitle("Logowanie do PJATK");
        // Przekazujemy scenę do okna
        stage.setScene(scene);
        // Gdy wszystko gotowe, można serwować
        stage.show();
    }

}