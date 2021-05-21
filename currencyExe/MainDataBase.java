package currencyExe;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainDataBase extends WindowAdapter {
	
	public static void main(String[] args) {
		new MainDataBase();
	}
		
	public MainDataBase() { 
		//open the data base
		DataBase.establishConnection(); 
	
		JFrame frame = new JFrame (); // straight forward , building the main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);		
		frame.add(new DrawData());	
		frame.setVisible(true);
		
		// event listener
		frame.addWindowListener(this);		
	}
	
	// close the data base
	public void windowClosing(WindowEvent e) {  
		DataBase.closeConnection();
	} 
}
