package Mmn14Q2;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

	//drawing all the graphics  on the screen and manages the reminders table
public class Draw  extends JPanel implements  ActionListener {
		
		private static final long serialVersionUID = 1L; //  to get read from warning
		
		private Integer [] yearOptions = {2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021} ;
		private Integer [] monthOptions = {1,2,3,4,5,6,7,8,9,10,11,12} ;	
		private Integer [] dayOptions = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31} ;
		private JButton add, show ;
		private JLabel response;
		private JComboBox<Integer> chooseYear, chooseMonth, chooseDay;
		private JTextField text;
		private Reminders remindersTable;
		private HandelFile handelFile;
		
		public Draw () {
			
			remindersTable = new Reminders();
			handelFile= new HandelFile();

			chooseYear = new JComboBox<Integer> (yearOptions); 
			chooseMonth = new JComboBox<Integer> (monthOptions); 
			chooseDay = new JComboBox<Integer> (dayOptions); 
			
			add = new JButton("Add");
			show = new JButton("show");
			text = new JTextField("Type you text here", 35); // 35 Character wide
			response = new JLabel();
			
			JPanel controls = new JPanel();	 // adding all the buttons and the labels to the north panel
			controls.add(chooseYear);
			controls.add(chooseMonth);
			controls.add(chooseDay);
			controls.add(add);
			controls.add(show);
			
			JPanel textField = new JPanel(); // the panel to the text field 
			textField.add(text);
			textField.add(response);

			// layouts				
		    this.setLayout(new BorderLayout());	
		    
		    add(controls, BorderLayout.NORTH);
		    add(textField, BorderLayout.CENTER);
		    	
			add.addActionListener(this);
			show.addActionListener(this);
		}
		
		public void paintComponent (Graphics g) {
				super.paintComponent(g);
		}
					    
		// adding new Reminder or showing existing Reminder
		public void actionPerformed (ActionEvent e) {	
			// if we adding new reminder
            if (e.getSource() == add) {
            	// construct new date to pass the reminder
            	Date tempDate = new Date ((int)chooseYear.getSelectedItem(),(int)chooseMonth.getSelectedItem(),(int) chooseDay.getSelectedItem() );
            	remindersTable.addReminder(tempDate , text.getText());   
            	response.setText("new reminder was adder");       
            	
            	//saves the reminders to the file
            	try {
					handelFile.saveAll(remindersTable.getHashTable());
				} 
            	catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            
            }
            else if ( e.getSource() == show) {
            	Date tempDate = new Date ((int)chooseYear.getSelectedItem(),(int)chooseMonth.getSelectedItem(),(int) chooseDay.getSelectedItem() );
            	String str= remindersTable.getReminder(tempDate);
            	text.setText(str);// show text on screen
            	response.setText("showing exisiting reminder"); 
            }        
		}			
}
