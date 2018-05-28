package components;

import sample.interfaces.IChat;
import server.userInfo;

public interface Handler {
    public IChat receive(userInfo userInfo);
}
