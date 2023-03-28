package Lez9;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

public class Client{
    public static void main(String[] args){
        int port=1338;
        try{
            SocketAddress address=new InetSocketAddress("localhost", port);
            SocketChannel client=SocketChannel.open(address);
            ByteBuffer buf=ByteBuffer.allocate(100);
            System.out.println(client.read(buf));
            String res = new String(buf.array(), StandardCharsets.UTF_8).trim();
            System.out.println(res);
        } catch (IOException e){System.err.println("ohno");}
    }
}

