package server;

import java.net.InetAddress;

public class ContactInfo {

    private String name;
    private InetAddress inetAddress;
    public ContactInfo(String name){
        this.name=name;
    }

    public ContactInfo(InetAddress name){
        this.inetAddress=name;
    }

    public String getName() {
        return name;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }
}
