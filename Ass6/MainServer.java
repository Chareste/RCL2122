package Ass6;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class MainServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        //String path = "https://localhost:"+port+"/test.jpeg";
        try(ServerSocket server = new ServerSocket(port)){
            while(true){
                try (Socket connectionSocket = server.accept();
                     DataInputStream fromClient = new DataInputStream(connectionSocket.getInputStream());
                    DataOutputStream outToClient = new DataOutputStream (connectionSocket.getOutputStream())
                ){
                    String fileName = fromClient.readUTF();
                    String path = "./src/"+fileName;
                    System.out.println(path);
                    try {
                        File file = new File(path);
                        int length = (int) file.length();
                        byte[] fileBytes = new byte[length];
                        outToClient.write(fileBytes, 0, length);
                        outToClient.flush();
                    } catch (FileNotFoundException e){
                        outToClient.writeUTF("404 not found");
                    }
                }

            }
        }
    }
}
