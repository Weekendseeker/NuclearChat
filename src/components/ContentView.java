package components;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ContentView extends VBox {
    public ContentView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/FXML/contentView.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void add(Node node){
        this.getChildren().add(node);
    }
}






