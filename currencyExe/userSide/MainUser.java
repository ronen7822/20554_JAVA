package currencyExe.userSide;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import currencyExe.DataBase;


public class MainUser extends WindowAdapter {
	
	public static void main(String[] args) {
		new  MainUser();
	} 
		 
	public MainUser() { 
		//open the data base 
		DataBase.establishConnection();  
	
	
		JFrame frame = new JFrame (); // straight forward , building the main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);		
		frame.add(new SignIn());	
		frame.setVisible(true);
		
		// event listener
		frame.addWindowListener(this);		
	}
	
	// close the data base
	public void windowClosing(WindowEvent e) {  
		DataBase.unlockAllUsers();
		DataBase.closeConnection();
	} 
}
