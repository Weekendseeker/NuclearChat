package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controllers.FormController;
import sample.controllers.mainController;
import sample.controllers.registrationController;
import sample.customs.chatView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        FormController.init(primaryStage);
        FormController.getInstance().newFormByNode(new registrationController());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
