package Mmn16Q2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    private DatagramSocket socket = null;
    private boolean moreClients = true;
    private final int BUFFER_LENGTH = 256;

    public Server() {
        try {
            socket = new DatagramSocket(8888);
            System.out.println("server ready");
        } 
        catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void start() {

        DatagramPacket packet;

        while (moreClients) {

            try {
                byte[] buf = new byte[BUFFER_LENGTH];

                //get message
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String received = (new String(packet.getData()).substring(0, packet.getLength()));
                System.out.println(received);

                //return the message
                packet = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());
                socket.send(packet);

            } 
            catch (IOException e) {
                e.printStackTrace();
                moreClients = false;
            }
        }
        socket.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}