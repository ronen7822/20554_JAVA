package Mmn16Q2;

public class Main {
	
    public static void main(String[] args) {
        Client client1 = new Client("localhost", 8888, "1", false);
        Client client2 = new Client("localhost", 8888, "2", true);
        client1.start();
        client2.start();
    }
}

