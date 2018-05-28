package sample.controllers;

import components.Handler;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import sample.customs.chatView;
import sample.customs.userView;
import sample.interfaces.IChat;
import sample.interfaces.animated;
import sample.interfaces.switchChat;

import server.Server;
import server.userInfo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController extends AnchorPane implements switchChat, Initializable , Handler, animated {

    @FXML
    private TextField search;

    @FXML
    private VBox usernameList;

    @FXML
    private AnchorPane chat;

    @FXML
    private Pane leftUi;

    @FXML
    private BorderPane chatContainer;

    private Server server;

    private userView userListView;


    public mainController(){
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/sample/FXML/chatMain.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        initServer();


    }

    @Override
    public void switchChat(IChat chatView) {
        chatContainer.setCenter((Node)chatView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public  synchronized IChat receive(userInfo userInfo) {
        chatView chatView =new chatView(userInfo);
        userListView=new userView(this,chatView,userInfo);


        Platform.runLater(new Runnable() {
            @Override
            public synchronized void run() {
                System.out.println("Список");
                usernameList.getChildren().add(userListView);
            }
        });

        return chatView;
    }

    public void initServer(){

        server=new Server(this);
}


    @FXML
    public void onClose(MouseEvent event){
        FormController.getInstance().getPrimaryStage().setIconified(true);
    }

    @FXML
    public void exitOnClose(MouseEvent event){

        FormController.getInstance().getPrimaryStage().close();
        Platform.exit();
        System.exit(0);

    }

    @Override
    public void Animate() {

        KeyFrame start=new KeyFrame(Duration.ZERO,
                new KeyValue(leftUi.opacityProperty(),0),
                new KeyValue(chatContainer.opacityProperty(),0));

        KeyFrame end=new KeyFrame(Duration.seconds(0.5),
                new KeyValue(leftUi.opacityProperty(),1),
                new KeyValue(chatContainer.opacityProperty(),1));

        Timeline slide = new Timeline(start,end);
        slide.play();
    }
}
