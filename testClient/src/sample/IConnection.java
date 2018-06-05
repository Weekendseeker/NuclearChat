package sample;

import components.Message;

public interface IConnection {


//    public void connect(Connection connection);
//    public void disconnect(Connection connection);
    public void receive( Message message);
//    public void registry(Connection connection, boolean d);


}
