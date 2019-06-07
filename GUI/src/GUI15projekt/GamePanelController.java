package GUI15projekt;


import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.util.Duration;

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

    private ImageView[][] imageView;
    private Image image;
    private WritableImage[][] images;
    private int[][] actualSetup;
    private int[][] correctSetup;
    private int[] actualBlank;
    private boolean allSwap;
    private Thread thread;
    private String nick;
    private byte level;
    private long startTime;
    private long stopTime;
    private Stage stage;
    private boolean play = false, replay = false;
    private ResultCellController[] cells;
    private FileOperations fo;

    @FXML
    public void initialize() {
        fo = new FileOperations();
        cells = fo.readCells();
        Task game = new Task() {
            @Override
            protected String call() throws Exception {
                startTime = System.currentTimeMillis();
                while (play) {
                    updateMessage("Twój czas: " + ((System.currentTimeMillis() - startTime) / 10) / 100.0);
                    if (Arrays.deepEquals(actualSetup, correctSetup)) {
                        stopTime = System.currentTimeMillis();
                        Platform.runLater(() -> win());
                    }
                    Thread.sleep(10);
                }
                return null;
            }
        };
        timeLabel.textProperty().bind(game.messageProperty());
        thread = new Thread(game);
        thread.setDaemon(true);
        loadResults();
    }

    @FXML
    void startGame(ActionEvent actionEvent) {
        if (!thread.isAlive()) {
            if (!replay) {
                shuffle();
                play = true;
                startButton.setText("Stop");
                thread.start();
            } else {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GamePanel.fxml"));
                    Parent root = fxmlLoader.load();
                    GamePanelController gpm = fxmlLoader.getController();
                    gpm.settings(allSwap, nick, level, stage);
                    gpm.cropImages(image);
                    Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
                    scene.setOnKeyPressed(gpm::keyClicked);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            stopTime = System.currentTimeMillis();
            stopGame();
        }
    }

    void win() {
        stopGame();
        ResultCellController you = new ResultCellController(nick, stopTime - startTime, level, allSwap);
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
        replay = true;
        startButton.setText("Restart");
    }

    void loadResults() {
        resultVBox.getChildren().remove(0, resultVBox.getChildren().size());
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

    void settings(boolean allSwap, String nick, byte level, Stage stage) {
        this.allSwap = allSwap;
        this.nick = nick;
        this.level = level;
        this.stage = stage;
    }

    void cropImages(Image image) {
        this.image = image;
        PixelReader pixelReader = image.getPixelReader();
        int columns=3 + level, rows=3 + level, widthOfCroped, heightOfCroped;
        images = new WritableImage[columns][rows];
        imageView = new ImageView[columns][rows];
        correctSetup = new int[columns][rows];
        actualSetup = new int[columns][rows];
        widthOfCroped = (int) (image.getWidth() / columns);
        heightOfCroped = (int) (image.getHeight() / rows);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                correctSetup[column][row] = actualSetup[column][row] = row * columns + column + 1;
                images[column][row] = new WritableImage(pixelReader, column * widthOfCroped, row * heightOfCroped, widthOfCroped, heightOfCroped);
                if (row == rows - 1 && column == columns - 1) {
                    correctSetup[column][row] = actualSetup[column][row] = 0;
                    images[column][row] = new WritableImage(widthOfCroped, heightOfCroped);
                    actualBlank = new int[]{column, row};
                }
                imageView[column][row] = new ImageView();
                imageView[column][row].setImage(images[column][row]);
                int finalColumn = column, finalRow = row;
                imageView[column][row].setOnMouseClicked(event -> mouseClicked(finalColumn, finalRow));
                imageView[column][row].setFitWidth(600 / rows);
                imageView[column][row].setFitHeight(heightOfCroped);
                imageView[column][row].setPickOnBounds(true);
                imageView[column][row].setPreserveRatio(true);
                Grid.add(imageView[column][row], column, row);
            }
        }
    }

    void mouseClicked(int columnA, int rowA) {
        if (allSwap) swap(columnA, rowA, actualBlank[0], actualBlank[1]);
        else if(Math.abs(columnA-actualBlank[0])<=1&&Math.abs(rowA-actualBlank[1])<=1&&Math.abs(columnA-actualBlank[0])!=Math.abs(rowA-actualBlank[1])){
            swap(columnA,rowA,actualBlank[0],actualBlank[1]);
        }
        /*else if (columnA == 0) { //TAK, CAŁE GO GUNWO UDAŁO MI SIĘ ZASTĄPIĆ JEDNĄ CHOLERNĄ LINIJKĄ, JESTEM DEBILEM
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
        }*/
    }

    void swap(int columnA, int rowA, int columnB, int rowB) {
        int inttmp = actualSetup[columnA][rowA];
        actualSetup[columnA][rowA] = actualSetup[columnB][rowB];
        actualSetup[columnB][rowB] = inttmp;
        actualBlank[0] = columnA;
        actualBlank[1] = rowA;
        WritableImage tmp = images[columnA][rowA];
        images[columnA][rowA] = images[columnB][rowB];
        images[columnB][rowB] = tmp;
        int indexA = Grid.getChildren().indexOf(imageView[columnA][rowA]);
        int indexB = Grid.getChildren().indexOf(imageView[columnB][rowB]);
        ((ImageView) Grid.getChildren().get(indexA)).setImage((images[columnA][rowA]));
        ((ImageView) Grid.getChildren().get(indexB)).setImage((images[columnB][rowB]));
    }

    void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < ((actualSetup.length < 5) ? 500 : 5000); i++) {
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
