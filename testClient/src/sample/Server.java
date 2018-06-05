package sample;

import components.Message;
import javafx.application.Platform;
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

    private IConnection connection;

    private Runnable listener =()->{

        try {
            while (true) {
                userSocket = socket.accept();

                for (User user : users) {
                    if (!(user.getSocket().getInetAddress().toString().equals(userSocket.getInetAddress()))) {
                        users.add(new User(userSocket));
                    }
                }

                ObjectInputStream in = new ObjectInputStream(userSocket.getInputStream());
                Message message=(Message) in.readObject();
                System.out.println(message.getContents().toString());

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        connection.receive(message);
                    }
                });
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        connection.receive(message);
//                    }
//                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    };


    public Server(IConnection connection){
        users=new ArrayList<>();
        this.connection=connection;


        try {
            socket=new ServerSocket(13);
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
