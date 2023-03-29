package Ass9;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ClientEcho{
    public static int DEFAULT_PORT=1339;
    public static String DEFAULT_HOST = "localhost";
    public static String DEFAULT_MSG = "hello client!";
    public static void main(String[] args) throws IOException{
        int port;
        String host, message;
        try{host=args[0];
        } catch (RuntimeException e){host=DEFAULT_HOST;}
        try{port=Integer.parseInt(args[1]);
        } catch (RuntimeException e){port=DEFAULT_PORT;}
        try{message=args[2];
        } catch (RuntimeException e){message=DEFAULT_MSG;}

        try{
            SocketAddress address=new InetSocketAddress(host, port);
            SocketChannel client=SocketChannel.open(address);
            System.out.println(client.getLocalAddress()+": trying connection on port "+port);
            ByteBuffer buf=ByteBuffer.allocate(50);

            byte[] str = message.getBytes(StandardCharsets.UTF_8);
            buf.put(str);
            buf.flip();
            client.write(buf);

            buf.clear();
            client.read(buf);
            String res = new String(buf.array(), StandardCharsets.UTF_8).trim();
            System.out.println(res);

        }catch (IOException e){System.err.println("ohno");}
    }
}
