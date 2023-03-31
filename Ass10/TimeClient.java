package Ass10;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TimeClient{
    public static void main(String[] args) throws IOException{

            InetAddress ia=InetAddress.getByName("239.255.1.3");
            byte[] buf = new byte[50];
            int port= 1331;
            DatagramPacket dp = new DatagramPacket(buf,buf.length,ia, port);
            DatagramSocket ms = new DatagramSocket(port);
            for(int i=0;i<10;i++){
                ms.receive(dp);
                String res = new String(dp.getData()).trim();
                System.out.println(res);
            }
        System.out.println("goodbye");
    }
}
