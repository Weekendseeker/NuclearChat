package server;

import components.Handler;
import components.Message;
import javafx.application.Platform;
import sample.interfaces.IChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class Server implements iConnection {

    private ServerSocket socket;
    private ConcurrentHashMap<Connection, IChat> map;

    private Socket userSocket;

    private IChat iChat;
    private Handler handler;
    private Thread thread;

    private Runnable listener = () -> {
        try {
            while (true) {
                new Connection(this, socket.accept());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    };


    public Server(Handler handler) {

        map = new ConcurrentHashMap<>();

        this.handler = handler;
            try {
                 socket = new ServerSocket(11);
            } catch (IOException e) {
                System.out.println("Problem with start server :(");
            }

        thread = new Thread(listener);
        thread.start();
        System.out.println("Server started...");
    }


    @Override
    public synchronized void receiveMSG(Connection connection, Message message) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (Map.Entry<Connection, IChat> entry : map.entrySet()) {
                    if (entry.getKey().getInetAddress().equals(connection.getInetAddress())) {
                        entry.getValue().receive(message);
                    }
                }
            }
        });
    }

    @Override
    public synchronized void connectionReady(Connection connection) {
        userSocket = connection.getSocket();
        System.out.println(userSocket.getInetAddress() + " " + "Подключён");
        if (!map.isEmpty()) {

            if (isCreate(userSocket))return;
                else {

                    iChat = handler.receive(new userInfo(userSocket.getInetAddress()));
                    map.put(connection, iChat);

                    System.out.println("Добавлен" + connection.getSocket().getPort());
                }
        }else{

        iChat = handler.receive(new userInfo(userSocket.getInetAddress()));
        map.put(connection, iChat);
        System.out.println(map.toString());
    }
}


    public boolean isCreate(Socket socket){
        for (Connection connection : map.keySet()) {
            if (connection.getInetAddress().equals(socket.getInetAddress())) return true;
        }
        return false;
    }
}
