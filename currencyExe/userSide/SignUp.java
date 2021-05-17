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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import currencyExe.DataBase;

public class SignUp extends JPanel implements ActionListener{
	
	private static final int MIN_LENGTH = 4;
	private static final int ID_LENGTH = 9;
	private static final long serialVersionUID = 1L; 
	private JButton NewUser;
	private JTextField id, name, password, rePassword;
	private JLabel response;	

	public SignUp() {	
		id = new JTextField("what is your id",10); 
		name =  new JTextField("what is your name",19); 
		password = new JTextField("enter your password",10); 
		rePassword = new JTextField("reenter your password",10); 
		NewUser = new JButton("create new user");
		response = new JLabel();
		
			
		JPanel controls = new JPanel();	 // adding all the buttons and the labels to the north panel
		controls.add(id);
		controls.add(name);
		controls.add(password);
		controls.add(rePassword);
		controls.add(NewUser);
		controls.add(response);
		
		// alignment of components
		id.setMaximumSize(new Dimension(250, 100));
		name.setMaximumSize(new Dimension(250, 100));
		password.setMaximumSize(new Dimension(250, 100));
		rePassword.setMaximumSize(new Dimension(250, 100));
		
		id.setAlignmentX(Component.CENTER_ALIGNMENT);
		password.setAlignmentX(Component.CENTER_ALIGNMENT);
		rePassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		NewUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JPanel res = new JPanel(); // the panel to the text field 
		res.add(response);
				
		// layouts	
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
	    this.setLayout(new BorderLayout());	    
	    add(controls, BorderLayout.NORTH);
	    add(res, BorderLayout.SOUTH);
	    	
	    NewUser.addActionListener(this);
	    	    
	}
	
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String idText = id.getText() ;
		String passwordText = password.getText() ;
	    
		if (! passwordText.equals(rePassword.getText()) ) 
			response.setText("passwords does not match");
		
		else if (passwordText.length() <= MIN_LENGTH) 
			response.setText("passwords to weak, enter more than 4 chars");
		
		else if (idText.length() != ID_LENGTH)
			response.setText("ivalid id number"); 
		
		else {
		
			int idNum = 0; 
			
		    try {
		      idNum = Integer.parseInt(idText);
		    }
		    catch (NumberFormatException nfe) {
		    	response.setText("id does not conatain charecters other than digits ");
		    	return;
		    }
		    
		    // check if the user is already in the data base
		    if (DataBase.userExsit(idText)) {
		    	response.setText("user alrady exsit with the same id ");
		    	return;
		    }
		    
		    int ans = JOptionPane.showConfirmDialog(this, "Would you like to continue?", "Question", JOptionPane.YES_NO_OPTION);
		    if (ans == JOptionPane.NO_OPTION)
		    	return;
		    	
		    // add the user to the data base, don forget to encrypt the password
		    DataBase.addUser(idNum, passwordText, name.getText());		    	    
		    response.setText("The user has been successfully added");			
		}
		
		this.resetText();		
	}
	
	// reset all the text in the text box
	public void resetText() {
		id.setText("what is your id"); 
		name.setText("what is your name");
		password.setText("enter your password");
		rePassword.setText("Reenter your password");
	}
}



