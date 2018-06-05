package sample;

import java.io.Serializable;

public class MsgPacket implements Serializable{

    String string;
    MsgPacket(String string){
        this.string=string;
    }

    private String getString(){
        return string;
    }
}
