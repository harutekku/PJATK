package GUI15projekt;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.Random;

public class GamePanelController {
    @FXML
    GridPane Grid;
    @FXML
    ImageView imageView[][];

    WritableImage[][] images;
    int[][] actualSetup;
    int[][] correctSetup;

    @FXML
    public void initialize(){
    }
    void cropImages(Image image, String difficultLevel){
        PixelReader pixelReader=image.getPixelReader();
        int columns,rows,widthOfCroped,heightOfCroped;
        switch (difficultLevel){
            case "Łatwy":
                columns=3;
                rows=3;
                break;
            case "Średni":
                columns=4;
                rows=4;
                break;
            case "Trudny":
                columns=5;
                rows=5;
                break;
            default:
                columns=1;
                rows=1;
                System.out.println("Błąd");
                System.exit(1);
                break;
        }
        widthOfCroped=(int)(image.getWidth()/columns);
        heightOfCroped=(int)(image.getHeight()/rows);
        images=new WritableImage[columns][rows];
        imageView=new ImageView[columns][rows];
        correctSetup=new int[columns][rows];
        actualSetup=new int[columns][rows];
        for(int row=0;row<rows;row++){
            for(int column=0;column<columns;column++){

                correctSetup[column][row]=actualSetup[column][row]=row*columns+column+1;
                images[column][row]=new WritableImage(pixelReader,column*widthOfCroped,row*heightOfCroped,widthOfCroped,heightOfCroped);

                if (row==rows-1&&column==columns-1){ //nadpisuje ostatni element zerem i pustym planem
                    correctSetup[column][row]=actualSetup[column][row]=0;
                    images[column][row]=new WritableImage(widthOfCroped,heightOfCroped);
                }

                imageView[column][row]=new ImageView();
                imageView[column][row].setImage(images[column][row]);
                int finalColumn = column, finalRow = row;
                imageView[column][row].setOnMouseClicked(event -> mouseClicked(finalColumn, finalRow));


                imageView[column][row].setFitWidth(widthOfCroped);
                imageView[column][row].setFitHeight(heightOfCroped);
                imageView[column][row].setPickOnBounds(true);
                imageView[column][row].setPreserveRatio(true);

                Grid.add(imageView[column][row],column,row);
            }
        }
    }

    void mouseClicked(int columnA, int rowA){
        if(columnA==0){
            if(rowA==0){
                if(actualSetup[columnA+1][rowA]==0)swap(columnA,rowA,columnA+1,rowA);
                else if(actualSetup[columnA][rowA+1]==0)swap(columnA,rowA,columnA,rowA+1);
            }
            else if(rowA==actualSetup.length-1){
                if(actualSetup[columnA+1][rowA]==0)swap(columnA,rowA,columnA+1,rowA);
                else if(actualSetup[columnA][rowA-1]==0)swap(columnA,rowA,columnA,rowA-1);
            }
            else{
                if(actualSetup[columnA+1][rowA]==0)swap(columnA,rowA,columnA+1,rowA);
                else if(actualSetup[columnA][rowA+1]==0)swap(columnA,rowA,columnA,rowA+1);
                else if(actualSetup[columnA][rowA-1]==0)swap(columnA,rowA,columnA,rowA-1);
            }
        }
        else if(columnA==actualSetup.length-1){
            if(rowA==0){
                if(actualSetup[columnA-1][rowA]==0)swap(columnA,rowA,columnA-1,rowA);
                else if(actualSetup[columnA][rowA+1]==0)swap(columnA,rowA,columnA,rowA+1);
            }
            else if(rowA==actualSetup.length-1){
                if(actualSetup[columnA-1][rowA]==0)swap(columnA,rowA,columnA-1,rowA);
                else if(actualSetup[columnA][rowA-1]==0)swap(columnA,rowA,columnA,rowA-1);
            }
            else{
                if(actualSetup[columnA-1][rowA]==0)swap(columnA,rowA,columnA-1,rowA);
                else if(actualSetup[columnA][rowA+1]==0)swap(columnA,rowA,columnA,rowA+1);
                else if(actualSetup[columnA][rowA-1]==0)swap(columnA,rowA,columnA,rowA-1);
            }
        }
        else{
            if(rowA==0){
                if(actualSetup[columnA-1][rowA]==0)swap(columnA,rowA,columnA-1,rowA);
                else if(actualSetup[columnA+1][rowA]==0)swap(columnA,rowA,columnA+1,rowA);
                else if(actualSetup[columnA][rowA+1]==0)swap(columnA,rowA,columnA,rowA+1);
            }
            else if(rowA==actualSetup.length-1){
                if(actualSetup[columnA-1][rowA]==0)swap(columnA,rowA,columnA-1,rowA);
                else if(actualSetup[columnA+1][rowA]==0)swap(columnA,rowA,columnA+1,rowA);
                else if(actualSetup[columnA][rowA-1]==0)swap(columnA,rowA,columnA,rowA-1);
            }
            else{
                if(actualSetup[columnA-1][rowA]==0)swap(columnA,rowA,columnA-1,rowA);
                else if(actualSetup[columnA][rowA+1]==0)swap(columnA,rowA,columnA,rowA+1);
                else if(actualSetup[columnA][rowA-1]==0)swap(columnA,rowA,columnA,rowA-1);
                else if(actualSetup[columnA+1][rowA]==0)swap(columnA,rowA,columnA+1,rowA);
            }
        }
//        int columnB=-1,rowB=-1;
//        for(int i=0;i<actualSetup.length;i++){
//            for(int j=0;j<actualSetup[i].length;j++){
//                if(actualSetup[j][i]==0){
//                    columnB=j;
//                    rowB=i;
//                    break;
//                }
//            }
//        }
        //swap(columnA,rowA,columnB,rowB);
        if(Arrays.deepEquals(actualSetup, correctSetup)) System.out.println("dobrze");
    }

    void swap(int columnA, int rowA, int columnB, int rowB) {
        int inttmp = actualSetup[columnA][rowA];
        actualSetup[columnA][rowA] = actualSetup[columnB][rowB];
        actualSetup[columnB][rowB] = inttmp;
        WritableImage tmp = images[columnA][rowA]; //zamieniam obrazy w tablicy
        images[columnA][rowA] = images[columnB][rowB];
        images[columnB][rowB] = tmp;
        int indexA = Grid.getChildren().indexOf(imageView[columnA][rowA]); //podmieniam na nowy obraz
        int indexB = Grid.getChildren().indexOf(imageView[columnB][rowB]);
        ((ImageView) Grid.getChildren().get(indexA)).setImage((images[columnA][rowA]));
        ((ImageView) Grid.getChildren().get(indexB)).setImage((images[columnB][rowB]));
    }
    void shuffle(){
        Random rand=new Random();
        for(int i=0;i<50;i++){
            mouseClicked(rand.nextInt(actualSetup.length),rand.nextInt(actualSetup.length));
//            try {
//                //Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
