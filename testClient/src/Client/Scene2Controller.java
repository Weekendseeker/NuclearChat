package Client;

import components.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import sample.IConnection;
import sample.Server;
import sample.User;


public class Scene2Controller implements IConnection, Initializable{

    @FXML
    private VBox chatBox;
    @FXML
    private TextArea ta1;
    @FXML
    private AnchorPane msgInterface;

    @FXML
    private TextField ip;

    private ArrayList<Content> contents;
    private ArrayList<User> users;
    private Date date;
    private String name="Gena";

    private ContentView contentView;
    private IRender render;

    private Socket listener;
    private Socket sender;

    private ObjectOutputStream out;
    private Message message;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        render=new RenderContent();
        contents = new ArrayList<>();
        users=new ArrayList<>();
        initServer();
    }

    private void initServer()  {
        Server server = new Server(this);
    }

    public void SendMessage(ActionEvent actionEvent) throws IOException {
        sendMsg();
    }

    private void updateUi(Message message)  {
        contentView = new ContentView();
        date=new Date();
        if(!message.getClient().equals(name)){
            contentView.setStyle("-fx-background-color:#F9BB69;-fx-margin:0,0,0,20;");
            contentView.setLayoutX(contentView.getLayoutX()+20);
        }

        for (Content content : message.getContents()) {
            try {
               contentView.add(render.Render(content));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        contentView.getChildren().add(getTimeLabel());
        chatBox.getChildren().add(contentView);
        ta1.setText(null);
    }

    private   void sendMsg() throws IOException{
        sender = new Socket(ip.getText(),11);

        if (ta1.getText()!=null) {
            contents.add(new Text(ta1.getText()));
        }
        message =new Message(contents,name);
        out=new ObjectOutputStream(sender.getOutputStream());
        out.writeObject(message);
        out.flush();
        sender.close();

        updateUi(message);
        clearList();
    }

    private void add(Content content ){
        contents.add(content);
    }

    private void clearList() {
        contents.clear();
    }

    public void dragDropp(DragEvent dragEvent) throws IOException {
        FileFilter fileFilter = new FileFilter();
        List<File> files=dragEvent.getDragboard().getFiles();

        for(File file:files){
            add(fileFilter.fileType(file));
            System.out.println(contents);
        }
    }

    public void dragOver(DragEvent dragEvent)throws IOException {
        msgInterface.setEffect(new GaussianBlur(10));
        dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    }

    public void OnEnter(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode()== KeyCode.ENTER){
            sendMsg();
        }
    }

    public void dragExited(DragEvent dragEvent) {
        msgInterface.setEffect(new GaussianBlur(0));
    }

    public void initSocket(){
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           listener=new Socket("192.168.1.93",13);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                       while (true){
                           ObjectInputStream objectInputStream = null;
                           try {
                               objectInputStream = new ObjectInputStream(listener.getInputStream());
                           } catch (IOException e) {
                               e.printStackTrace();
                           }

                           User user= null;
                           try {
                               user = (User)objectInputStream.readObject();
                               users.add(user);
                           } catch (IOException e) {
                               e.printStackTrace();
                           } catch (ClassNotFoundException e) {
                               e.printStackTrace();
                           }

                       System.out.println(user.getName());
                       }
                   }
               }).start();
    }

    @Override
    public void receive(Message message) {
        updateUi(message);
    }

    private Label getTimeLabel(){
        Label timeL=new Label();
        String formatTime=(new SimpleDateFormat("HH:mm").format(date));
        timeL.setText(formatTime);
        timeL.fontProperty().setValue(new Font("Calibri",10));

        return timeL;
    }

    public void initdata(String name){
        this.name=name;
        System.out.println(name);
    }
}
