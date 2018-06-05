package components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {



    private String clientname;
    private ArrayList<Content> contents;

    public Message(ArrayList<Content> content , String name){
        this.clientname=name;
        this.contents=content;
    }

    public String getClient() {
        return clientname;
    }
    public List<Content> getContents() {
        return contents;
    }
}
