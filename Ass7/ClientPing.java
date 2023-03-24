package Ass7;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Random;

public class ClientPing {
    public static void main(String[] args) throws IOException {

        //controllo argomenti e gestione degli errori
        /*if(args.length < 2){
            System.err.println("ERR - not enough arguments");
            System.exit(1); }

        String name = args[0];
        int port = 0;
        try{ port = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            System.err.println("ERR - port is NaN");
            System.exit(1);
        }*/ int port = 8080;
        InetAddress ip = InetAddress.getByName("localhost");
        Random rand = new Random();
        String str = "se stampi questo mi sa che non dovresti";
        int lostPackets = 0;
        long minRTT = 999999;
        long maxRTT = 0;
        double totRTT = 0;

        try (DatagramSocket clientSocket = new DatagramSocket(6666)) {
            //invio dieci datagrammi separati
            for (int i=0; i<10; i++) {
                try {
                    clientSocket.setSoTimeout(2000);

                    long timestamp = System.currentTimeMillis();
                    str = "PING "+i+" "+timestamp;
                    byte[] buffer = str.getBytes();

                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, port);
                    Thread.sleep(rand.nextInt(500));
                    clientSocket.send(packet);

                    byte[] bufReceiver = new byte[100];
                    DatagramPacket receivedPacket = new DatagramPacket(bufReceiver, bufReceiver.length);

                    clientSocket.receive(receivedPacket);

                    long rtt = System.currentTimeMillis()-timestamp;
                    if(rtt<minRTT) minRTT=rtt;
                    if(rtt>maxRTT) maxRTT=rtt;
                    totRTT+=rtt;

                    System.out.println(str+" RTT: "+rtt+" ms");

                } catch (InterruptedException e) { System.out.println("ù");
                } catch (SocketTimeoutException e) {
                    System.out.println(str+" RTT: * ");
                    lostPackets++;
                }
            }
        }
        String avgRTT = String.format("%.2f",totRTT/(10-lostPackets));
        System.out.println("---- PING STATISTICS ----");
        System.out.println("10 packets transmitted, "+(10-lostPackets)+" packets received, "+(lostPackets*10)+"% packet loss");
        System.out.println("round-trip (ms) /min/avg/max = "+minRTT+"/"+avgRTT+"/"+maxRTT);

    }

}