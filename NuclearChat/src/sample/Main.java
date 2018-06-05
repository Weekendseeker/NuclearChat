package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controllers.FormController;
import sample.controllers.RegistrationController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        FormController.init(primaryStage);
        FormController.getInstance().newFormByNode(new RegistrationController());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
