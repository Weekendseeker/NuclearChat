package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import sample.Connection;
import sample.Server;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

    public class registrationController implements Initializable {

        @FXML
        private TextField username;

        @FXML
        private TextField userpass;

        private Connection connection;

        private ObjectInputStream objectInputStream;
        private ObjectOutputStream objectOutputStream;
        private BufferedReader input;
        private PrintWriter output;
        private Socket socket;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            try {
                //Server server=new Server();
                socket =new Socket("localhost",12);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void reg(ActionEvent event) {
//            try {
//
//                String s=username.getText();
//                output=new PrintWriter(socket.getOutputStream(),true);
//                output.println(s);
//                socket.close();
//
               FormController.getInstance().switchingScene("scene2");
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }


