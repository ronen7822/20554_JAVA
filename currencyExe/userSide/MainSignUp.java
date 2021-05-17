package currencyExe.userSide;

import javax.swing.JFrame;

public class MainSignUp {
	
	public MainSignUp() {
		JFrame frame = new JFrame (); // straight forward , building the main frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 300);		
		frame.add(new SignUp());	
		frame.setVisible(true);
	}
	
}



