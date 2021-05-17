package currencyExe;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DrawData extends JPanel implements  ActionListener {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> currencyOptions, currencyOptions2 ; 
	private JButton add, remove ;
	private JTextField from, ratio;
	private JLabel response;	

	public DrawData() {	
		add = new JButton("add");
		remove = new JButton("remove");
		from = new JTextField("type the currency you want to add",19); 
		ratio = new JTextField("type the ratio",8); 
		response = new JLabel();

		currencyOptions= new JComboBox<String>(DataBase.getCurrencies());
		currencyOptions2= new JComboBox<String>(DataBase.getCurrencies());
			
		JPanel controls = new JPanel();	 // adding all the buttons and the labels to the north panel
		controls.add(from);
		controls.add(currencyOptions);		
		controls.add(ratio);
		controls.add(add);
		controls.add(response);
		
		JPanel answer = new JPanel(); // the panel to the text field 
		answer.add(response);
		
		JPanel removal= new JPanel(); // for removing
		removal.add(currencyOptions2);	
		removal.add(remove);
		
		// layouts				
	    this.setLayout(new BorderLayout());	    
	    add(controls, BorderLayout.NORTH);
	    add(answer, BorderLayout.CENTER);
	    add(removal, BorderLayout.SOUTH);
	    	
	    add.addActionListener(this);
	    remove.addActionListener(this);
	    	    
	}
	
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
}
			    
	// one of the buttons was clicked
	public void actionPerformed (ActionEvent e) {	
		    
       	// button clicked was "add"
       	if (e.getSource() == add) {
    		String newCurrency = from.getText();
           	String ratioText = ratio.getText();
           	
       		try {
       			// try to convert the text into double if fails prints error to the user
       			Double ratio =  Double.parseDouble(ratioText);
       			// if the new currency is not in the data base
           		if (! DataBase.currencyExistInTable(newCurrency)) {
           			// the ratio relative to the shekel
           			ratio = ratio * DataBase.rate(1, (String) currencyOptions.getSelectedItem(), "ILS");
           		
           			DataBase.addCurrency(newCurrency, ratio);
           			response.setText("the currency: " + newCurrency+ " was added sucsessfully" ); 
           			// repaint the options
           			this.updateOptions();
           		}
           		else 
           			response.setText("the currency: " + newCurrency+ " is alreay in the data base" );  
       		}
       		catch (NumberFormatException ex) {  	
    			response.setText("Given String is not parsable to double" ); 
    		} 
       	}
        // button clicked was "remove"
       	else if (e.getSource() == remove){       		
       		String currencyToRemove = (String)currencyOptions.getSelectedItem();
       		 
       		if (currencyToRemove.equals("ILS")) {    			
       			response.setText("Can not remove ILS" );
       			return;
       		}
       		// remove the currency from the the data base
       		DataBase.removeCurrency( currencyToRemove );
       		response.setText("the currency: " + currencyToRemove + " was removed sucsessfully" );
       		this.updateOptions();
       	}       		
	}
	
	public void updateOptions() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( DataBase.getCurrencies() );
		currencyOptions.setModel( model );
		currencyOptions2.setModel( model );
		this.paint(getGraphics());
	}
	
}
