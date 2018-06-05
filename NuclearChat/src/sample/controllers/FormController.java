package sample.controllers;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.interfaces.Animated;

import java.io.IOException;

public final class FormController {

    private static Stage primaryStage;
    private static FormController instance;

    private FormController(){}


    public static void init(Stage stage){
        if(instance==null)FormController.instance=new FormController();
        FormController.primaryStage=stage;
    }


    public void newFormByFxml(String nameFXML){
        primaryStage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(nameFXML+".fxml"));
        try {
            Pane pane=fxmlLoader.load();
            primaryStage.setScene(new Scene(pane,pane.getPrefWidth(),pane.getPrefHeight()));
        }catch (IOException e){e.printStackTrace();}
        primaryStage.show();
    }

    public  void newFormByNode(Pane pane){
        primaryStage=new Stage();

        Pane root=pane;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setScene(new Scene(root,root.getPrefWidth(),root.getPrefHeight()));
        primaryStage.getScene().setFill(Color.TRANSPARENT);
        primaryStage.show();
    }

    public void animation(Animated node){
        Pane nowPane =(Pane)primaryStage.getScene().getRoot();
        Pane nextPane=(Pane)node;
        Animated anime= node;

        StackPane stackPane=new StackPane(nowPane);
        stackPane.setStyle("-fx-background-color:transparent;");

        double width=nowPane.getWidth();

        stackPane.getChildren().add((Pane)nextPane);

        Animated rgb=(RegistrationController) nowPane;


        nowPane.setCache(true);
        nowPane.setCacheShape(true);
        nowPane.setCacheHint(CacheHint.SPEED);

        nextPane.setCache(true);
        nextPane.setCacheShape(true);
        nextPane.setCacheHint(CacheHint.SPEED);


        KeyFrame start=new KeyFrame(Duration.ZERO,
                new KeyValue(nextPane.translateXProperty(),width-30),
                new KeyValue(nowPane.translateXProperty(),0));

        KeyFrame frame=new KeyFrame(Duration.seconds(2),
                new KeyValue(nextPane.translateXProperty(),0),
                new KeyValue(nowPane.translateXProperty(), -width+25));



        Timeline slide = new Timeline(start,frame);

        slide.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stackPane.getChildren().remove(nowPane);
                anime.Animate();
            }
        });
        rgb.Animate();


        primaryStage.setScene(new Scene(stackPane, nextPane.getPrefWidth(),nextPane.getPrefHeight()));
        primaryStage.getScene().setFill(Color.TRANSPARENT);
        primaryStage.show();
        slide.play();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static FormController getInstance() {
        return instance;
    }
}
