package Mmn16Q1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// handling exchange request
public class ThreadServer extends Thread{
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private ExchangeRate exchange = new ExchangeRate();
	private Request rqst;
	private String str;
	
	// construct the thread
	public ThreadServer(Socket s){
		socket = s;
		try {
			out = new PrintWriter (socket.getOutputStream(), true);
			in = new BufferedReader  ( new InputStreamReader(socket.getInputStream()));
		} 		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		// the action
		try {
			while ((str = in.readLine()) != null) {  
				rqst = new Request (str); 
				out.println("after exchange:"+exchange.rate( rqst.getSum(), rqst.getFrom(), rqst.getTo() ));  
			}
		
			// closing the streams and the server
			out.close();
			in.close();
			socket.close();
			System.out.println("good bye");
		}
		catch(IOException e) {
			e.printStackTrace();			
		}
	}
}
