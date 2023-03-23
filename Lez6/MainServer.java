package Lez6;

import java.io.*;
import java.net.*;

import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException{
        //int port = new Random().nextInt(100);
        String fileName= "file.txt";
        try(ServerSocket listenSocket = new ServerSocket(20)) {
            while (true){
                try (Socket connectionSocket = listenSocket.accept();
                     DataOutputStream outToClient = new DataOutputStream (connectionSocket.getOutputStream())
                    ){
                         // access the requested file
                         File file = new File(fileName);
                         // convert file to a byte array
                         int length = (int) file.length();
                         try(FileInputStream inputFile = new FileInputStream (fileName)){
                             byte[] fileBytes = new byte[length];
                             inputFile.read(fileBytes);
                             outToClient.write(fileBytes,0,length);
                             outToClient.flush();
                         }
                         catch(FileNotFoundException e) {}

                     }
            }
        }

    }
}
