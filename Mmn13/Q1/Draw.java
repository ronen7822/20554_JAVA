package Mmn13Qst1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// add some doc
public class Draw  extends JPanel implements ItemListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // to get rid from the warring
	private String [] alphabet = {" ","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r",
			"s","t","u","v","w","x","y","z"} ;
	private String letters ="" ;
	
	private JComboBox<String> choose;
	private JLabel label, guess;
	private JButton quit ;
	private HangMan man ;
	private boolean finish;

		public Draw ( ) {
			
		man = new HangMan(); // the logic of the game
	
		choose = new JComboBox<String> (alphabet);
		label = new JLabel("select a letter");
		guess = new JLabel(man.showSoFar());
		quit = new JButton("New Game");
		
		JPanel controls = new JPanel();	 // adding all the buttons and the labels to the north panel
		controls.add(guess);
		controls.add(choose);
		controls.add(label);
		controls.add(quit);
		
	    this.setLayout(new BorderLayout());	
	    add(controls, BorderLayout.NORTH);
	    
		choose.addItemListener(this);		
		quit.addActionListener(this);

	}
		public void paintComponent (Graphics g) {
			if (man.getCount() == 0 || man.getCount() >= 7) // if we hit the end if the game than clear the panel
				super.paintComponent(g);
			else
				this.drawHang(g);
		}
		
		// drawing the hang man
		public void drawHang (Graphics g) {
			
			if(man.getCount() >= 1)	
				g.drawOval(85, 70, 30, 30);
			if (man.getCount() >= 2)
				g.drawLine(100, 100, 130, 130);
			if (man.getCount() >= 3 )
				g.drawLine(100, 100, 70, 130);
			if (man.getCount() >= 4)
				g.drawLine(100, 100, 100, 150);
			if (man.getCount() >= 5 )
				g.drawLine(100, 150, 130, 180);
			if (man.getCount() >= 6)
				g.drawLine(100, 150, 70, 180);			
		}
		
		// if the state combo box is changed
	    public void itemStateChanged (ItemEvent e)
	    { 	
	    	if ( e.getStateChange() == ItemEvent.SELECTED && ! choose.getSelectedItem().equals(" ") ) { // in order to get rid from double calling to the method
	    		
	    		char ch = ((String) choose.getSelectedItem()).charAt(0); // getting the chosen char
	    		
	    		if ( ! man.allreadyChosen(ch) ) //  if the character is already chosen
	    			letters += " " + choose.getSelectedItem();
	    		
	    		man.guessLetter(ch);	
	    		finish = man.finished();
	    		
	    		guess.setText(man.showSoFar());
	    		
	    		if (man.getCount() >= 7 ) { // the hang man is dead
	    			label.setText(" Game Over ");
	    			this.reset();
	    		}
	    		else if (finish) { // the user guessed the word successfully
	    			label.setText("You Won ");
	    			this.reset();
	    		}
	    		else {
	    			label.setText("letters so far: " + letters);
	    			repaint(); 
	    		}    
	    	}
	    }
	    
	    // quitting the current game and creating a new game
		public void actionPerformed (ActionEvent e) {			
				this.reset();
				label.setText( "New Game");
				repaint();
		}	
		private void reset() {
			man.reset() ; // reset the counter
			letters =""; // reset the string
			guess.setText(man.showSoFar());
			choose.setSelectedIndex(0) ;
		}
}
