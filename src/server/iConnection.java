package server;

import components.Message;

import java.io.IOException;

public interface iConnection {

    public void receiveMSG(Connection connection, Message message);
    public void connectionReady(Connection connection) throws IOException, InterruptedException;

}
