package Ass9;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class ServerEcho{
    public static int DEFAULT_PORT=1339;
    public static void main(String[] args) throws IOException{
        int port;
        try{ port=Integer.parseInt(args[0]);
        } catch (RuntimeException e){port=DEFAULT_PORT;}

        System.out.println("Listening for connections on port "+port);

        ServerSocketChannel serverCh=ServerSocketChannel.open();
        ServerSocket socket=serverCh.socket();
        InetSocketAddress address=new InetSocketAddress(port);
        socket.bind(address);
        serverCh.configureBlocking(false);
        Selector selector=Selector.open();
        serverCh.register(selector, SelectionKey.OP_ACCEPT); //registro selettore di accesso

        while(true){
            try{selector.select();
            } catch (IOException e){
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> readyKeys=selector.selectedKeys();
            Iterator<SelectionKey> iterator=readyKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key=iterator.next();
                iterator.remove();
                // rimuove la chiave dal Selected Set, ma non dal registered Set
                try{
                    ByteBuffer buf = ByteBuffer.allocate(50);
                    if(key.isAcceptable()){
                        ServerSocketChannel server=(ServerSocketChannel) key.channel();
                        SocketChannel client=server.accept();
                        System.out.println("Accepted connection from "+client);
                        client.configureBlocking(false);
                        SelectionKey key2=client.register(selector, SelectionKey.OP_READ);

                    }
                    else if (key.isReadable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        client.read(buf);
                        System.out.println("lettura fatta");

                        System.out.println("dentro writable");
                        //Thread.sleep(1000);

                       /* String res = new String(buf.array(), StandardCharsets.UTF_8).trim();
                        System.out.println(res);
                        res += " - echoed by server";
                        System.out.println(res);
                        buf.clear();*/
                        buf.put(" - echoed by server"/*res*/.getBytes(StandardCharsets.UTF_8));
                        //System.out.println(buf);
                        buf.flip();
                        client.write(buf);
                        key.cancel();
                    }

                } catch (IOException e){key.cancel();}
            }
        }
    }
}
