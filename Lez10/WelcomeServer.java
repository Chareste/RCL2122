package Lez10;

import java.io.IOException;
import java.net.*;

/* Definire un Server WelcomeServer, che
          invia su un gruppo di multicast (welcomegroup*), ad intervalli regolari, un messaggio di «welcome».
          attende tra un invio ed il successivo un intervallo di tempo simulato mediante il metodo sleep( ).
         • Definire un client WelcomeClient che si unisce a welcomegroup e riceve un messaggio di welcome, quindi termina.*/
public class WelcomeServer{
        public static void main(String[] args) throws IOException{
            int port = 1330;
            String addressName = "239.255.1.3";
            try (MulticastSocket ms = new MulticastSocket(port)){
                InetSocketAddress group=new InetSocketAddress(InetAddress.getByName(addressName), port);
                NetworkInterface netIf=NetworkInterface.getByName("wlan1");
                ms.joinGroup(group, netIf);
                byte[] data="welcome".getBytes();
                DatagramPacket dp=new DatagramPacket(data, data.length, InetAddress.getByName(addressName), port);
                for(int i=0; i<10;i++){
                    ms.send(dp);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e){System.err.println("aaaaaaaaaaa");}
        }
}
