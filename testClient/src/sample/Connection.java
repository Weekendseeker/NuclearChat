package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Connection  {

    private Socket socket;
    private Socket pool;

    private IConnection iconnection;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Thread poolThread, myServerThread;

    public  Connection(IConnection iconnection, Socket pool,Socket socket, User user){
        this.iconnection=iconnection;
        this.socket=socket;
        this.pool=pool;


        poolThread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectInputStream objectInputStream=new ObjectInputStream(pool.getInputStream());

                    ArrayList<User> users =(ArrayList<User>) objectInputStream.readObject();
                    System.out.println();


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
