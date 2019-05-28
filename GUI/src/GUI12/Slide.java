package GUI12;

import javafx.scene.layout.Background;

public class Slide {

    private String message;
    private String title;
    private String comment;
    private Background background;

    public Slide(String message, String title, String comment, Background background) {
        this.message = message;
        this.title = title;
        this.comment = comment;
        this.background=background;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment){
        this.comment=comment;
    }
    public void setBackground(Background background){
        this.background=background;
    }
    public Background getBackground(){
        return background;
    }
}
