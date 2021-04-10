package Mmn16Q1;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// drawing the graphics and handling the logic of the buttons
public class Draw  extends JPanel implements  ActionListener{
	private static final long serialVersionUID = 1L; //  to get read from warning
	
	private String [] currencyTo = {"ILS","USD","EUR","CNY","CAD"} ; // all possible currencies to convert
	private String [] currencyFrom = {"ILS","USD","EUR","CNY","CAD"} ;	
	private JButton convert ;
	private JLabel response;
	private JComboBox<String> from, to ;
	private JTextField sum;
	private Client client ;

	
	public Draw (Client client) {
		
		this.client = client; // the thread of the user client
		
		from = new JComboBox<String> (currencyFrom);  // adding the controls of the program
		to = new JComboBox<String> (currencyTo); 		
		convert = new JButton("convert");
		sum = new JTextField("Type the sum", 15); 
		response = new JLabel();
		
		JPanel controls = new JPanel();	 // adding all the buttons and the labels to the north panel
		controls.add(sum);
		controls.add(from);
		controls.add(to);
		controls.add(convert);
		
		JPanel answer = new JPanel(); // the panel to the text field 
		answer.add(response);

		// layouts				
	    this.setLayout(new BorderLayout());	    
	    add(controls, BorderLayout.NORTH);
	    add(answer, BorderLayout.CENTER);
	    	
	    convert.addActionListener(this);
	    
	}
	
	public void paintComponent (Graphics g) {
			super.paintComponent(g);
	}
				    
	// when the convert button is clicked the conversion performed
	public void actionPerformed (ActionEvent e) {	
		
		String currTo = (String) to.getSelectedItem(); //gets the currencies to convert
       	String currFrom = (String) from.getSelectedItem();  
       	String textSum = sum.getText();
       	// try to convert the text into double if fails prints error to the user
       	try {
       		Double sum =   Double.parseDouble(textSum);
       		response.setText(client.run(sum, currFrom, currTo) ); 
       	}
    	catch (NumberFormatException ex) {  	
    		response.setText("Given String is not parsable to double" ); 
    	} 
	}
	
}
