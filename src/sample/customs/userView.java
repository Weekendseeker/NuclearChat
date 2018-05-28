package sample.customs;
import java.io.IOException;

import sample.interfaces.IChat;
import sample.interfaces.switchChat;
import server.userInfo;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class userView extends HBox {

    @FXML private Label username;
    @FXML private Button Button;
    @FXML private Circle avatar;

    private sample.interfaces.switchChat switchChat;
    private chatView chatView;
    private IChat chat;
    private userInfo user;


    public userView(){}

    public userView(switchChat switchChat, chatView chatView, userInfo userInfo) {

            this.switchChat=switchChat;
            this.chatView=chatView;
            this.chat=chatView;
            this.user=userInfo;
            init();
    }

    public void init(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/FXML/userListView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        username.setText(user.getInetAddress().toString());

        this.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                switchChat.switchChat(chatView);
            }
        });

    }

    public chatView getChat(){
        return this.chatView;
    }
}
