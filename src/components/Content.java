package components;

import java.io.Serializable;

public class Content implements Serializable {

    static int id;

    public Content(){
        id++;
    }

    public static int getId() {
        return id;
    }

    public Object getContent(){
        return this;
    }

    public Object getObject(){
        return this;
    }
}
