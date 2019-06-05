package GUI15projekt;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GamePanelController {
    @FXML
    Button startButton;
    @FXML
    Label timeLabel;
    @FXML
    VBox resultVBox;
    @FXML
    GridPane Grid;
    @FXML
    ImageView imageView[][];

    WritableImage[][] images;
    int[][] actualSetup;
    int[][] correctSetup;
    int[] actualBlank;
    boolean allSwap;
    Thread game,resetGame;
    String nick;
    byte level;
    long startTime;
    long stopTime;
    Stage stage;
    boolean play = false;
    ResultCellController[] cells;
    FileOperations fo;

    @FXML
    public void initialize() {
        fo = new FileOperations();
        cells = fo.readCells();
        game=resetGame = new Thread(() -> {
            startTime = System.currentTimeMillis();
            while (play) {
                Platform.runLater(() -> {
                    timeLabel.setText("Twój czas: " + ((System.currentTimeMillis() - startTime) / 10) / 100.0);
                    if (Arrays.deepEquals(actualSetup, correctSetup)) {
                        stopTime = System.currentTimeMillis();
                        win();
                    }
                });
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        game.setDaemon(true);
        loadResults();
    }

    @FXML
    void startGame(ActionEvent actionEvent) {
        if (!game.isAlive()) {
            shuffle();
            play = true;
            game.start();
            startButton.setText("Stop");
        } else {
            stopTime = System.currentTimeMillis();
            stopGame();
        }
    }

    void win() {
        stopGame();
        ResultCellController you = new ResultCellController(nick, stopTime - startTime,level,allSwap);
        ArrayList<ResultCellController> list = new ArrayList<ResultCellController>(Arrays.asList(cells));
        list.add(you);
        list.sort(ResultCellController::compareTo);
        while (list.size() > 10) list.remove(list.size() - 1);
        cells = list.toArray(cells);
        fo.writeCells(cells);
        loadResults();
    }

    void stopGame() {
        play = false;
        //game=resetGame;

        startButton.setDisable(true);
    }

    void loadResults() {
        resultVBox.getChildren().remove(0,resultVBox.getChildren().size());
        for (int i = 0; i < cells.length; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultCell.fxml"));
            loader.setController(cells[i]);
            try {
                Pane pane = loader.load();
                resultVBox.getChildren().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void settings(boolean allSwap, String nick, byte level) {
        this.allSwap = allSwap;
        this.nick = nick;
    }

    void cropImages(Image image, String difficultLevel) {
        PixelReader pixelReader = image.getPixelReader();
        int columns, rows, widthOfCroped, heightOfCroped;
        switch (difficultLevel) {
            case "Łatwy":
                columns = 3;
                rows = 3;
                break;
            case "Średni":
                columns = 4;
                rows = 4;
                break;
            case "Trudny":
                columns = 5;
                rows = 5;
                break;
            default:
                columns = 1;
                rows = 1;
                System.out.println("Błąd");
                System.exit(1);
                break;
        }
        widthOfCroped = (int) (image.getWidth() / columns);
        heightOfCroped = (int) (image.getHeight() / rows);
        images = new WritableImage[columns][rows];
        imageView = new ImageView[columns][rows];
        correctSetup = new int[columns][rows];
        actualSetup = new int[columns][rows];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {

                correctSetup[column][row] = actualSetup[column][row] = row * columns + column + 1;
                images[column][row] = new WritableImage(pixelReader, column * widthOfCroped, row * heightOfCroped, widthOfCroped, heightOfCroped);

                if (row == rows - 1 && column == columns - 1) { //nadpisuje ostatni element zerem i pustym planem
                    correctSetup[column][row] = actualSetup[column][row] = 0;
                    images[column][row] = new WritableImage(widthOfCroped, heightOfCroped);
                    actualBlank = new int[]{column, row};
                }

                imageView[column][row] = new ImageView();
                imageView[column][row].setImage(images[column][row]);
                int finalColumn = column, finalRow = row;
                imageView[column][row].setOnMouseClicked(event -> mouseClicked(finalColumn, finalRow));


                imageView[column][row].setFitWidth(widthOfCroped);
                imageView[column][row].setFitHeight(heightOfCroped);
                imageView[column][row].setPickOnBounds(true);
                imageView[column][row].setPreserveRatio(true);
                imageView[column][row].setFitWidth(600 / rows);

                Grid.add(imageView[column][row], column, row);
            }
        }
    }

    void mouseClicked(int columnA, int rowA) {
        if (allSwap) swap(columnA, rowA, actualBlank[0], actualBlank[1]);
        else if (columnA == 0) {
            if (rowA == 0) {
                if (actualSetup[columnA + 1][rowA] == 0) swap(columnA, rowA, columnA + 1, rowA);
                else if (actualSetup[columnA][rowA + 1] == 0) swap(columnA, rowA, columnA, rowA + 1);
            } else if (rowA == actualSetup.length - 1) {
                if (actualSetup[columnA + 1][rowA] == 0) swap(columnA, rowA, columnA + 1, rowA);
                else if (actualSetup[columnA][rowA - 1] == 0) swap(columnA, rowA, columnA, rowA - 1);
            } else {
                if (actualSetup[columnA + 1][rowA] == 0) swap(columnA, rowA, columnA + 1, rowA);
                else if (actualSetup[columnA][rowA + 1] == 0) swap(columnA, rowA, columnA, rowA + 1);
                else if (actualSetup[columnA][rowA - 1] == 0) swap(columnA, rowA, columnA, rowA - 1);
            }
        } else if (columnA == actualSetup.length - 1) {
            if (rowA == 0) {
                if (actualSetup[columnA - 1][rowA] == 0) swap(columnA, rowA, columnA - 1, rowA);
                else if (actualSetup[columnA][rowA + 1] == 0) swap(columnA, rowA, columnA, rowA + 1);
            } else if (rowA == actualSetup.length - 1) {
                if (actualSetup[columnA - 1][rowA] == 0) swap(columnA, rowA, columnA - 1, rowA);
                else if (actualSetup[columnA][rowA - 1] == 0) swap(columnA, rowA, columnA, rowA - 1);
            } else {
                if (actualSetup[columnA - 1][rowA] == 0) swap(columnA, rowA, columnA - 1, rowA);
                else if (actualSetup[columnA][rowA + 1] == 0) swap(columnA, rowA, columnA, rowA + 1);
                else if (actualSetup[columnA][rowA - 1] == 0) swap(columnA, rowA, columnA, rowA - 1);
            }
        } else {
            if (rowA == 0) {
                if (actualSetup[columnA - 1][rowA] == 0) swap(columnA, rowA, columnA - 1, rowA);
                else if (actualSetup[columnA + 1][rowA] == 0) swap(columnA, rowA, columnA + 1, rowA);
                else if (actualSetup[columnA][rowA + 1] == 0) swap(columnA, rowA, columnA, rowA + 1);
            } else if (rowA == actualSetup.length - 1) {
                if (actualSetup[columnA - 1][rowA] == 0) swap(columnA, rowA, columnA - 1, rowA);
                else if (actualSetup[columnA + 1][rowA] == 0) swap(columnA, rowA, columnA + 1, rowA);
                else if (actualSetup[columnA][rowA - 1] == 0) swap(columnA, rowA, columnA, rowA - 1);
            } else {
                if (actualSetup[columnA - 1][rowA] == 0) swap(columnA, rowA, columnA - 1, rowA);
                else if (actualSetup[columnA][rowA + 1] == 0) swap(columnA, rowA, columnA, rowA + 1);
                else if (actualSetup[columnA][rowA - 1] == 0) swap(columnA, rowA, columnA, rowA - 1);
                else if (actualSetup[columnA + 1][rowA] == 0) swap(columnA, rowA, columnA + 1, rowA);
            }
        }
    }

    void swap(int columnA, int rowA, int columnB, int rowB) {
        int inttmp = actualSetup[columnA][rowA]; //zmieniam oznaczenie pustego elementu
        actualSetup[columnA][rowA] = actualSetup[columnB][rowB];
        actualSetup[columnB][rowB] = inttmp;
        actualBlank[0] = columnA;
        actualBlank[1] = rowA;
        WritableImage tmp = images[columnA][rowA]; //zamieniam obrazy w tablicy
        images[columnA][rowA] = images[columnB][rowB];
        images[columnB][rowB] = tmp;
        int indexA = Grid.getChildren().indexOf(imageView[columnA][rowA]); //podmieniam na nowy obraz
        int indexB = Grid.getChildren().indexOf(imageView[columnB][rowB]);
        ((ImageView) Grid.getChildren().get(indexA)).setImage((images[columnA][rowA]));
        ((ImageView) Grid.getChildren().get(indexB)).setImage((images[columnB][rowB]));
    }

    void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < ((actualSetup.length < 5) ? 100 : 500); i++) {
            arrowMove(rand.nextInt(4));
        }
    }

    void arrowMove(int direction) {
        switch (direction) {
            case 0:
                if (actualBlank[1] > 0) swap(actualBlank[0], actualBlank[1] - 1, actualBlank[0], actualBlank[1]);
                break;
            case 1:
                if (actualBlank[0] < actualSetup.length - 1)
                    swap(actualBlank[0] + 1, actualBlank[1], actualBlank[0], actualBlank[1]);
                break;
            case 2:
                if (actualBlank[1] < actualSetup.length - 1)
                    swap(actualBlank[0], actualBlank[1] + 1, actualBlank[0], actualBlank[1]);
                break;
            case 3:
                if (actualBlank[0] > 0) swap(actualBlank[0] - 1, actualBlank[1], actualBlank[0], actualBlank[1]);
                break;
            default:
                System.out.println("bad");
                break;
        }
    }

    public void keyClicked(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                arrowMove(0);
                break;
            case RIGHT:
                arrowMove(1);
                break;
            case DOWN:
                arrowMove(2);
                break;
            case LEFT:
                arrowMove(3);
                break;
            default:
                break;
        }
    }
}
