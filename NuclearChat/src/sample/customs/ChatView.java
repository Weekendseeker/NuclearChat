package sample.customs;

import sample.interfaces.IChat;
import sample.interfaces.IRender;
import sample.interfaces.UpdateUi;
import server.ContactInfo;
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

/**
 Данный класс отвечает за работу функционал чата
 */

public class ChatView extends VBox implements UpdateUi,Initializable, IChat {

    public static int id;

    @FXML
    private VBox chat;
    @FXML
    private TextArea output;

    private ArrayList<Content> contents;

    private Date date;
    private String name="Marvin";
    private ContentView ContentView;

    private ContactInfo ContactInfo;
    private Socket socket;

    public ChatView(ContactInfo ContactInfo){

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/sample/FXML/chatView.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.ContactInfo = ContactInfo;
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

        //Обновление графического интерфейса
        private void updateUi(Message message)  {
            ContentView =new ContentView();
            IRender render=new RenderContent();
            date=new Date();


            if(!message.getClient().equals(name)){
               ContentView.setStyle("-fx-background-color:#F9BB69;-fx-border-radius: 10; -fx-background-radius: 10;");
            }

            for (Content content : message.getContents()) {
                try {
                    ContentView.getChildren().add(render.Render(content));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ContentView.getChildren().add(getTimeLabel());
            chat.getChildren().add(ContentView);
            output.setText(null);
        }


        //Отправка сообщение
        public void sendMsg() throws IOException{
            if(!output.getText().isEmpty()){contents.add(new Text(output.getText()));}

             socket=new Socket(ContactInfo.getName(),13);

             ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
             Message msg=new Message(contents,name);

             out.writeObject(msg);
             out.flush();
             updateUi(msg);
             clearList();
        }


        //Событие кнопки отправки сообщения
        @FXML
        public void sendButton() throws IOException{
            sendMsg();
        }


        @FXML
        public void scroll(ScrollEvent scrollEvent){
            System.out.println("Work");
            output.setPrefHeight(output.getPrefHeight()+10);
        }


        //Drag'n'Dropp
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
        public void dragExited(DragEvent dragEvent) {
            output.setEffect(new GaussianBlur(0));
        }


        //Добавить контент
        private void addContent(Content content ){
            contents.add(content);
        }

        //Очистить список от контента
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

        //Прослушивание входящих сообщений
        @Override
        public void receive(Message message) {
            updateUi(message);
        }
}
