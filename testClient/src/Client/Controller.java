package Client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    ServerSocket socket;
    Socket socket1;

    public void start(ActionEvent event) {
        FormController.getInstance().newForm("sample/sample");
    }

    public void newForm(ActionEvent event) {
        FormController.getInstance().newFormAndClose("Registration");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
