package sample.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import sample.interfaces.Animated;

import java.io.IOException;

public class RegistrationController extends AnchorPane implements Animated {


    @FXML
    private ImageView registerBackground;

    @FXML
    private ImageView enterButton;

    @FXML
    private HBox ui;

    public RegistrationController(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/sample/FXML/autorizationView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void click(MouseEvent event){
        FormController.getInstance().animation(new MainController());
    }

    @Override
    public void Animate() {

        ui.setVisible(false);
        KeyFrame start=new KeyFrame(Duration.ZERO,
                          new KeyValue(registerBackground.opacityProperty(),1),
                          new KeyValue(ui.opacityProperty(),1));
        KeyFrame end =new KeyFrame(Duration.seconds(0.7),
                      new KeyValue(registerBackground.opacityProperty(),0),
                      new KeyValue(ui.opacityProperty(),0));

        Timeline timeline=new Timeline(start,end);
        timeline.play();

    }
}
