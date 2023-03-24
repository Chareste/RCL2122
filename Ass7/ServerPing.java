package Ass7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Random;

public class ServerPing {
    public static void main(String[] args) throws IOException {
        int port = /*Integer.parseInt(args[0]);*/ 8080;
        try (DatagramSocket server = new DatagramSocket(port)) {

            Random rand = new Random();
            int delay;
            String action;

            while (true) {
                byte[] buffer = new byte[100];
                DatagramPacket received = new DatagramPacket(buffer, buffer.length);
                server.receive(received);
                InetAddress IPAddress = received.getAddress();
                DatagramPacket echo = new DatagramPacket(buffer, buffer.length, IPAddress, received.getPort());

                String data = new String(received.getData());

                if(data.startsWith("PING") && rand.nextInt(4)!=0){
                    delay = rand.nextInt(1000);
                    Thread.sleep(delay);
                    server.send(echo);
                    action = "delayed "+delay+" ms";
                }
                else action = "not sent";

                System.out.println(IPAddress+":"+port+"> "+data+" ACTION: "+action);


            }
    } catch (InterruptedException e) {}
    }
}

