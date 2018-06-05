package VirtualServer;

import components.Message;
import sample.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {

    private ServerSocket socket;
    private ArrayList<User> users;

    private Socket userSocket;
    private BufferedReader input;
    private BufferedWriter output;


    private Runnable listener =()->{

        try {
            while (true) {
                userSocket = socket.accept();
                System.out.println(userSocket);

                if(users.size()==0){
                    users.add(new User(userSocket));
                }else {
                    for (User user : users) {
                        if (!(user.getSocket().getInetAddress().toString().equals(userSocket.getInetAddress().toString()))) {
                            users.add(new User(userSocket));
                        }
                    }
                }

                for (User user:users){
                    ObjectOutputStream in = new ObjectOutputStream(user.getSocket().getOutputStream());
                    in.writeObject(users.get(users.size()-1));
                    in.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    };


    public Server(){
        users=new ArrayList<>();

        try {
            socket=new ServerSocket(12);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(listener);
        thread.start();
    }


    private void receiveMsg(Message msgPacket){
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(users.get(0).getSocket().getOutputStream());
            objectOutputStream.writeObject(msgPacket);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
