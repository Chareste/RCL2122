package Ass10;

/*
Definire un Server TimeServer, che

        invia su un gruppo di multicast  dategroup, ad intervalli regolari, la data e l’ora.
        attende tra un invio ed il successivo un intervallo di tempo simulata mediante il metodo  sleep().
        L’indirizzo IP di dategroup viene introdotto  da linea di comando.

        Definire quindi un client TimeClient che si unisce a dategroup e riceve, per dieci volte consecutive, data ed ora, le visualizza, quindi termina.

*/


import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;

public class TimeServer{
    static String DEFAULT_ADDRESS = "239.255.1.3";

    public static void main(String[] args) throws IOException{
        
        int port = 1331;
        String addressName;
        try{ addressName=args[0];
        } catch (RuntimeException e){addressName=DEFAULT_ADDRESS;}

        try (MulticastSocket ms = new MulticastSocket(port)){
            InetSocketAddress group=new InetSocketAddress(InetAddress.getByName(addressName), port);
            NetworkInterface netIf=NetworkInterface.getByName("wlan1");
            ms.joinGroup(group, netIf);
            while(true){
                String currentDate =LocalDateTime.now().toString();
                byte[] data=currentDate.getBytes();
                DatagramPacket dp=new DatagramPacket(data, data.length, InetAddress.getByName(addressName), port);
                ms.send(dp);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){System.err.println("Goodbye");}
    }

}
