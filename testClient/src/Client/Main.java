package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
            FormController.init(primaryStage);
            FormController.getInstance().newForm("scene2");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

final class FormController{
        private FormController(){}

        private static Stage primarystage;
        private static FormController instance;
        private Controller controller;

        public static void init(Stage primarystage){
            if(instance==null) instance=new FormController();
            FormController.primarystage=primarystage;
        }

        public void switchingScene(String nameFxml){
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(nameFxml+".fxml"));
            try {
                Pane pane=fxmlLoader.load();
                primarystage.setScene(new Scene(pane, pane.getPrefWidth(),pane.getPrefHeight()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void newForm(String nameFxml){
            primarystage=new Stage();
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(nameFxml+".fxml"));
            controller=fxmlLoader.getController();
            try{
                Pane pane =fxmlLoader.load();
                primarystage.setScene(new Scene(pane, pane.getPrefWidth(),pane.getPrefHeight()));
            }catch (IOException e){
                e.printStackTrace();
            }
            primarystage.show();
        }

        public void newFormAndClose(String newForm){
            primarystage.close();
            newForm(newForm);
        }

        public Controller getController(){
            return controller;

        }

        public static FormController getInstance(){
            return instance;
        }


}
