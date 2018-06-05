package sample.customs;
import java.io.IOException;

import sample.interfaces.IChat;
import sample.interfaces.SwitchChat;
import server.ContactInfo;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;

public class ContactView extends HBox {

    /**Класс отображение контанка */



    @FXML private Label username;
    @FXML private Button Button;
    @FXML private Circle avatar;

    private SwitchChat SwitchChat;
    private ChatView ChatView;
    private IChat chat;
    private ContactInfo user;

    public ContactView(){}

    public ContactView(SwitchChat SwitchChat, ChatView ChatView, ContactInfo ContactInfo) {

            this.SwitchChat = SwitchChat;
            this.ChatView = ChatView;
            this.chat= ChatView;
            this.user= ContactInfo;
            init();
    }

    public void init(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/FXML/contactListView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        username.setText(user.getInetAddress().toString());

        //При нажатие на контакт переключить чат
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SwitchChat.switchChat(ChatView);
            }
        });

    }

    public ChatView getChat(){
        return this.ChatView;
    }
}
