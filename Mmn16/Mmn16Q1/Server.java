package Mmn16Q1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// the main sever handling all the requests
public class Server {  
	
	static int count = 1;
	//establishing the sever
	public static void main(String[] args) { 
	
		ServerSocket srv = null;
		boolean listening = true;
		
		try { 						
			srv = new ServerSocket(8888);   // create new sever socket
			System.out.println("server ready");
			Socket socket = null; 
			
			while(listening) { // Handle exchange with new thread
				socket = srv.accept();
				System.out.println("new client number:"+(count++));
				new ThreadServer(socket).start(); 
			}
			
			srv.close();
			System.out.println("good bye main server ends");
		}
		catch(IOException e) {
			e.printStackTrace();			
		} 

	}
}
