package Mmn16Q1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

// the client who performs the exchanges
public class Client { 
	
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String host = "localhost";
	private boolean failToConnect = false; // uses for closing the client
	
	// connect to the server
	public Client() {
		// TODO Auto-generated method stub
		
		try {
			socket = new Socket (host, 8888);
			System.out.println("client ready");
			out = new PrintWriter (socket.getOutputStream(), true);
			in = new BufferedReader  ( new InputStreamReader(socket.getInputStream()));
		} 
		catch (ConnectException e) {
			failToConnect = true;
			System.out.println("Faild to connect");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String run (double sum, String from, String to) {
		
		// the string for the sever to convert
		String str = sum+ " \""  +from+ "\"  " + " \"" +to+ "\"" ;
		String ret = null;
		
		// send the string to the server in order to process
		try {
			out.println(str);
			System.out.println( (ret = in.readLine()) );
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	// close the client
	public void close() {
		try {
			// if we initially  fail to connect we don't have anything to close
			if (!failToConnect) {
				in.close();
				out.close();
				socket.close();
				System.out.println( "good bye client" );
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
