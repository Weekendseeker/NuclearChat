package server;

import java.net.InetAddress;

public class userInfo {

    private String name;
    private InetAddress inetAddress;
    public userInfo(String name){
        this.name=name;
    }

    public userInfo(InetAddress name){
        this.inetAddress=name;
    }

    public String getName() {
        return name;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }
}
