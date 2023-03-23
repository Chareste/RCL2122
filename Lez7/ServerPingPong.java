package Lez7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPingPong {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try (Socket connectionSocket = server.accept();
                     DataInputStream fromClient = new DataInputStream(connectionSocket.getInputStream());
                     DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream())
                ) {
                    if(fromClient.readUTF().equals("Ping")){
                        Thread.sleep(1000);
                        outToClient.writeUTF("Pong");
                        outToClient.flush();
                        break;
                    }
                } catch (InterruptedException e) {}
            }
        }
    }
}

