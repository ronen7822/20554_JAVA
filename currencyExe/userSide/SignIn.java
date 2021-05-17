package currencyExe.userSide;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import currencyExe.DataBase;


public class SignIn extends JPanel implements  ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton signIn, signUp ;
	private JTextField id, password;
	private JLabel response;	

	public SignIn() {	
		id = new JTextField("enter your id",19); 
		password = new JTextField("enter your password",8); 
		signIn = new JButton("sign in");
		signUp = new JButton("sign up");
		response = new JLabel();
		
			
		JPanel controls = new JPanel();	 // adding all the buttons and the labels to the north panel
		System.out.println("a");
		controls.add(id);
		controls.add(password);		
		controls.add(signIn);
		controls.add(response);
		
		// alignment of components
		id.setMaximumSize(new Dimension(250, 100));
		password.setMaximumSize(new Dimension(250, 100));
		password.setAlignmentX(Component.CENTER_ALIGNMENT);
		signIn.setAlignmentX(Component.CENTER_ALIGNMENT);
		response.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JPanel newUser = new JPanel(); // the panel to the text field 
		newUser.add(signUp);
				
		// layouts	
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
	    this.setLayout(new BorderLayout());	    
	    add(controls, BorderLayout.NORTH);
	    add(newUser, BorderLayout.SOUTH);
	    	
	    signIn.addActionListener(this); 
	    signUp.addActionListener(this); 
	    	    
	}
	
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
}
			    
	// one of the buttons was clicked
	public void actionPerformed (ActionEvent e) {	
		    
		// opens the singing in option in new tab
		if ( e.getSource() == signUp) {
			new MainSignUp();
		} 
		else if (e.getSource() == signIn) {
					
			String idText = id.getText();
			//String passwordText =  password.getText();
			int idNum = 0;
			
		    try {
			      idNum = Integer.parseInt(idText);
		    }
			catch (NumberFormatException nfe) {
			   	response.setText("invalid id");
			   	return;
		    }
			
			if (! DataBase.userExsit(idText) ) {
				response.setText("no such user exsit, please sign up");
				return;
			}
			
			else if (! DataBase.correctPassword( idNum, password.getText() ) ) {
				response.setText("incorrect password");
			}
			
			else {
				response.setText("logging in");
			}
		}

    }



	

}
	

	


