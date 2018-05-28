package sample.customs;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import sample.interfaces.IChat;
import sample.interfaces.updateUi;
import server.userInfo;
import components.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.*;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;


public class chatView extends VBox implements updateUi,Initializable, IChat {

    public static int id;

    @FXML
    private VBox chat;
    @FXML
    private TextArea output;

    private ArrayList<Content> contents;

    private Date date;
    private String name="Marvin";
    private contentView contentView;

    private userInfo userInfo;
    private Socket socket;

    public chatView(userInfo userInfo){

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/sample/FXML/chatView.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.userInfo=userInfo;
        id++;

        System.out.println("Создан чат:"+id);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contents=new ArrayList<>();
    }

    @Override
    public void update(Label label) {
        this.getChildren().add(label);
    }


        private void updateUi(Message message)  {
        contentView=new contentView();
        IRender render=new RenderContent();
        date=new Date();


        if(!message.getClient().equals(name)){
            contentView.setStyle("-fx-background-color:#F9BB69;-fx-border-radius: 10; -fx-background-radius: 10;");
        }

        for (Content content : message.getContents()) {
            try {
                contentView.getChildren().add(render.Render(content));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        contentView.getChildren().add(getTimeLabel());
        chat.getChildren().add(contentView);
        output.setText(null);
    }



    public void sendMsg() throws IOException{
        if(!output.getText().isEmpty()){contents.add(new Text(output.getText()));}
        socket=new Socket(userInfo.getName(),13);
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        Message msg=new Message(contents,name);
        out.writeObject(msg);
        out.flush();


        updateUi(msg);
        clearList();
    }

    @FXML
    public void send() throws IOException{
        sendMsg();
    }
    @FXML
    public void scroll(ScrollEvent scrollEvent){
        System.out.println("Work");
        output.setPrefHeight(output.getPrefHeight()+10);
    }

    @FXML
    public void dragDropp(DragEvent dragEvent) throws IOException {
        FileFilter fileFilter = new FileFilter();
        List<File> files=dragEvent.getDragboard().getFiles();

        for(File file:files){
            addContent(fileFilter.fileType(file));
            System.out.println(contents);
        }
    }

    @FXML
    public void dragOver(DragEvent dragEvent)throws IOException {
        output.setEffect(new GaussianBlur(10));
        dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    }
    @FXML
    public void OnEnter(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode()== KeyCode.ENTER){
            sendMsg();
        }
    }

    @FXML
    public void wrapRe(KeyEvent event){

       // text=output.lookup(".text");

       // output.setPrefRowCount(++i);


        //System.out.println();
        //System.out.println(output.getParagraphs().toString());
    }

    @FXML
    public void dragExited(DragEvent dragEvent) {
        output.setEffect(new GaussianBlur(0));
    }


    private void addContent(Content content ){
        contents.add(content);
    }
    private void clearList() {
        contents.clear();
    }

    private Label getTimeLabel(){
        Label timeL=new Label();
        String formatTime=(new SimpleDateFormat("HH:mm").format(date));
        timeL.setText(formatTime);
        timeL.fontProperty().setValue(new Font("Calibri",10));

        return timeL;
    }


    @Override
    public void receive(Message message) {
            updateUi(message);
    }
}
