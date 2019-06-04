package GUI13;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class SliderController {
    // Słowo kluczowe final sprawia,
    // że nie można zmienić wartości zmiennej.
    public static final int VALUTE_TO_COMPLETE = 1000;

    @FXML
    private Text name;

    @FXML
    private ProgressBar progress;

    Task<Integer> progressTask;
    @FXML
    public void initialize() {

        // Tworzymy zadanie
        progressTask = new Task<>() {
            @Override
            protected Integer call() throws Exception {
                int value = 0;
                while (!isCancelled()) {
                    value++;
                    updateMessage("Osiągnięta wartość: " + value);
                    updateProgress(value, VALUTE_TO_COMPLETE);
                    Thread.sleep(100);
                    if (value >=VALUTE_TO_COMPLETE) return value;
                }
                return value;
            }
        };


        // Dowiązujemy wartości
        progress.progressProperty().bind(progressTask.progressProperty());
        name.textProperty().bind(progressTask.messageProperty());

        // Uruchamiamy
        Thread thread = new Thread(progressTask);
        thread.setDaemon(true);
        thread.start();

    }

    @FXML
    void stop(ActionEvent actionEvent) {
        progressTask.cancel();
    }
}