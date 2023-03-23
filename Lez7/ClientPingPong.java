package Lez7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientPingPong {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        try(Socket client = new Socket()){
            client.bind(new InetSocketAddress(1337));
            client.connect(new InetSocketAddress(port));
            while(true){
                try(
                    DataInputStream fromServer = new DataInputStream(client.getInputStream());
                    DataOutputStream toServer = new DataOutputStream(client.getOutputStream())
                ){
                    toServer.writeUTF("Ping");
                    Thread.sleep(1000);
                    if(fromServer.readUTF().equals("Pong")){
                        System.out.println("Timeout");
                    }
                    toServer.flush();
                    break;
                } catch (InterruptedException e) {}
            }
        }
    }
}
