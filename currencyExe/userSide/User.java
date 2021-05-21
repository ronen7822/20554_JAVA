package currencyExe.userSide;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import currencyExe.DataBase;
 
public class User extends WindowAdapter {
	
	private final int idNum;
	
	public User(int idNum) {
		
		this.idNum = idNum;
		
		JFrame frame = new JFrame ("User Transaction"); 
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 300);		 
		frame.add(new UserTransaction (idNum));	
		frame.setVisible(true);
		
		// event listener
		frame.addWindowListener(this);
	}
	
	// unlock the user in the data base
	public void windowClosing(WindowEvent e) {  
		DataBase.unlockUser(idNum);
	} 
}
