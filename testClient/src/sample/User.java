package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class User implements Serializable{

        //private static int uid;
        private String name;
        private String pass;
        private InetAddress inetAddress;

        transient private Socket socket;
        transient private BufferedReader input;
        transient private BufferedWriter out;
        transient private Thread thread;

        private Runnable rega=()->{
            try {
                input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                parser(input.readLine());
                this.socket.close();
                this.thread.interrupt();

            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        public User(Socket socket){
            this.socket=socket;
            this.inetAddress=socket.getInetAddress();
            thread=new Thread(rega);
            thread.start();
           // uid++;
        }

        public void parser(String input){
            String name="";
            String pass="";
            String strBuff="";
            char buff;
            for (int i=0;i<input.length();i++){
                buff=input.charAt(i);
                if(buff=='|'){
                    name=strBuff;
                    strBuff="";
                }
                strBuff+=buff;
            }
            pass=strBuff;
            pass=pass.substring(1);

            this.name=name;
            this.pass=pass;

            System.out.println(this.name +""+this.pass);
        }


        public void setPass(String pass){
            this.pass=pass;
        }
        public void setName(String name){this.name=name;}
        public void setSocket(Socket socket){ this.socket=socket; }
        public String getPass() {return pass; }
        public String getName() { return name; }
        public Socket getSocket(){
            return this.socket;
        }

    }

