package components;

import sample.interfaces.IChat;
import server.userInfo;

public interface ChatCreator {
    public IChat createChat(userInfo userInfo);
}
