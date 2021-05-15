package Mmn16Q2;

import java.io.IOException;
import java.net.*;

public class Client extends Thread {
    private String host;
    private byte[] buffer;
    private int port;
    private boolean isWaitingToReceiveMessages;
    private String name;

    final int NUM_OF_MESSAGE = 10,
            TIME_OUT = 10000, //ten seconds
    		BUFFER_LENGTH = 256;

    public Client(String host, int port, String name, boolean isWaitingToReceiveMessages) {
        this.host = host;
        this.isWaitingToReceiveMessages = isWaitingToReceiveMessages;
        this.port = port;
        this.name = name;
        buffer = new byte[BUFFER_LENGTH];
    }

    public void run() {

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(host);
            socket.setSoTimeout(TIME_OUT);
            for (int i = 0; i < NUM_OF_MESSAGE; i++) {

                String message = Integer.toString(i);

                //send message
                buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(packet);
                packet = new DatagramPacket(buffer, buffer.length);

                //get response
                socket.receive(packet);
                String received = (new String(packet.getData()).substring(0, packet.getLength()));

                while (isWaitingToReceiveMessages && !(received.equals(message))) {
                    socket.receive(packet);
                    received = (new String(packet.getData()).substring(0, packet.getLength()));
                }

                System.out.println("Name: " + name + " message: " + received + " is \"waiting-mode\":  " + isWaitingToReceiveMessages);
            }

            socket.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}