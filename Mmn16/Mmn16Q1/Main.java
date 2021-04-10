package Mmn16Q1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

// the client
public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client();
		JFrame frame = new JFrame ("test"); // straight forward , building the main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);	
		Draw panel = new Draw(client);		
		frame.add(panel);		
		frame.setVisible(true);
		
		// fixes a bug when closing the frame the sever collapses
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	client.close();
                System.exit(0);
            }
        });
				
	}
}