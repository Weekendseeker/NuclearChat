package server;

import components.Message;
import sample.interfaces.IChat;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Connection implements  Serializable {

        //private static int uid;
        private String name;
        private InetAddress inetAddress;
        private iConnection iConnection;
        private Thread rxThread;
        private IChat iChat;


        transient private Socket socket;
        //transient private BufferedReader input;
        transient private BufferedWriter out;
        transient private ObjectInputStream input;



        public Connection(iConnection iConnection , Socket socket) throws IOException {
            this.socket=socket;
            this.inetAddress=socket.getInetAddress();
            this.iConnection=iConnection;
            input=new ObjectInputStream(this.socket.getInputStream());

            rxThread=new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    try {
                        try {
                            iConnection.connectionReady(Connection.this);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (!rxThread.isInterrupted()){
                                iConnection.receiveMSG(Connection.this,(Message) input.readObject());
                                socket.close();
                        }
                    } catch (IOException e) {
                        try {
                            socket.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

            rxThread.start();
        }

        public void setName(String name){this.name=name;}
        public void setSocket(Socket socket){ this.socket=socket; }

        public String getName() { return name; }


        public Socket getSocket(){
            return this.socket;
        }

        public InetAddress getInetAddress() {
        return inetAddress;
    }

        @Override
        public int hashCode() {
        return inetAddress.hashCode();
    }
}

