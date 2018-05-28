package server;

import components.Message;

import java.io.IOException;

public interface iConnection {

    public void receiveMSG(User connection, Message message);
    public void connectionReady(User user) throws IOException, InterruptedException;

}
