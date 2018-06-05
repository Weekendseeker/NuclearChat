package sample;


import java.io.Serializable;
import java.util.ArrayList;

public class UserPool implements Serializable {


    ArrayList<User> users;

    public UserPool(ArrayList<User> userList){
        users=new ArrayList<>();
        users=userList;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
