package Mmn14Q2;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		
	 	JFrame frame = new JFrame ("test"); // straight forward , building the main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		Draw panel = new Draw( );		
		frame.add(panel);		
		frame.setVisible(true); 
	}

}
