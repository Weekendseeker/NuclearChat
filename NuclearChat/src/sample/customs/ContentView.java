package sample.customs;

import components.Content;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ContentView extends VBox{

    /**Класс контейнер для отображения контента*/


    public ContentView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/FXML/contentView.fxml"));
        loader.setController(this);
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addContent(Node node){
        this.getChildren().add(node);
    }
}
