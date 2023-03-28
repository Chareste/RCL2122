package Lez9;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;


/*  Scrivere un programma JAVA che implementa un server TCP che apre una listening socket su una porta e resta in attesa di
    richieste di connessione.
        ● Quando arriva una richiesta di connessione, il server accetta la connessione, trasferisce al client un messaggio
            ("HelloClient") e poi chiude la connessione.
        ● Usare multiplexed NIO (canali non bloccanti e il selettore, e ovviamente i buffer di tipo ByteBuffer).
        ● Per il client potete usare un client telnet.
1. Opzione più semplice:
    ● come primo esercizio potete sviluppare un programma in cui quando la serverSocketChannel ha connessioni da accettare
    (key.isAcceptable() è vera) il server scrive subito sulla socketChannel restituita dall'operazione di accept() e chiude la connessione.
2. Opzione più completa (vedi esempio IntGenServer sulle slide)
    ● Se key.isAcceptable() è verificata, la socketChannel restituita dall'operazione di accept viene registrata sul selettore
    (con interesse all'operazione di WRITE) e il messaggio viene inviato quando il canale è pronto per la scrittura (key.isWritable è true).

 */
public class Server{
    public static int DEFAULT_PORT = 1338;
    public static void main(String[] args) throws IOException{
        int port;
        try{ port = Integer.parseInt(args[0]);
        }catch (RuntimeException e){ port=DEFAULT_PORT;}
        System.out.println("Listening for connections on port " + port);

        ServerSocketChannel serverCh = ServerSocketChannel.open();
        ServerSocket socket = serverCh.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        socket.bind(address);
        serverCh.configureBlocking(false);
        Selector selector = Selector.open();
        serverCh.register(selector, SelectionKey.OP_ACCEPT); //registro selettore di accesso

        while(true){
            try{
                selector.select();
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
                    if(key.isAcceptable()){
                        ServerSocketChannel server=(ServerSocketChannel) key.channel();
                        SocketChannel client=server.accept();
                        System.out.println("Accepted connection from "+client);
                        client.configureBlocking(false);
                        SelectionKey key2=client.register(selector, SelectionKey.OP_WRITE);
                        //preparo buffer e invio

                        //System.out.println("pallefinewhatever");
                    }
                    else if(key.isWritable()){
                        //System.out.println("palledentroelseif");
                        Thread.sleep(1000);
                        SocketChannel client = (SocketChannel) key.channel();
                        byte[] str = "HelloClient".getBytes(StandardCharsets.UTF_8);
                        //System.out.println(new String(str, StandardCharsets.UTF_8));
                        ByteBuffer output=ByteBuffer.allocate(str.length);
                        //ByteBuffer output=new ByteBuffer()
                        output.put(str);
                        //System.out.println(output);
                        output.flip();
                        client.write(output);
                    }

                } catch (IOException e){
                    key.cancel();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }

            }
        }



    }
}
