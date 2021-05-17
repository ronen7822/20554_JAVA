package currencyExe.userSide;

import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import currencyExe.DataBase;

public class UserTransaction extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> currencyOptions ; 
	private JButton add, getTotalValue ;
	private JTextField sum;
	private JLabel response;	
	private int idNum; 

	public UserTransaction(int idNum) {	
		
		this.idNum = idNum;
		
		add = new JButton("add");
		getTotalValue = new JButton("show total value");
		sum = new JTextField("type the sum",8); 
		response = new JLabel();

		currencyOptions= new JComboBox<String>(DataBase.getCurrencies());
			
		JPanel controls = new JPanel();	 // adding all the buttons and the labels to the north panel
		controls.add(currencyOptions);		
		controls.add(sum);
		controls.add(add);
		controls.add(getTotalValue);

		
		JPanel answer = new JPanel(); // the panel to the text field 
		answer.add(response);
		

		
		// layouts				
	    this.setLayout(new BorderLayout());	    
	    add(controls, BorderLayout.NORTH);
	    add(answer, BorderLayout.CENTER);

	    	
	    add.addActionListener(this);
	    getTotalValue.addActionListener(this);
	    
	    	    
	}
	
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String currencyTo = (String) currencyOptions.getSelectedItem();
		
		if (e.getSource() == getTotalValue) { 
			double sum = DataBase.getTotalValue(this.idNum);
			response.setText("your value is: " + DataBase.rate (sum, "ILS",  currencyTo) + " " + currencyTo );  
		}
		else if (e.getSource() == add ) {
			
		 	String sumText = sum.getText();
		 	try {
		 		Double amount =  Double.parseDouble(sumText);
		 		if (amount > 0) {
		 			DataBase.addCurrencyToUser(idNum, amount, currencyTo );
		 			response.setText("amount :" + amount + " of the currency :" +currencyTo + " was added sucsessfully ");
		 		}
		 		else 
		 			response.setText("please insert positive value ");
		 	}
	       	catch (NumberFormatException ex) {  	
	    			response.setText("Given String is not parsable to double" ); 
	    			
	    	} 
		}
		
	}
	

}