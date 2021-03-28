package Mmn13Q2;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Timer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


//drawing all the graphics  on the screen and manages the rules of the trivia quiz
public class Draw  extends JPanel implements ItemListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; //  to get read from warning
	
	protected static boolean timeOver;
	private final int DELAY = 5000; // 5 seconds
	private Integer [] options = {0,1,2,3,4} ;	// the different options for answers
	private JComboBox<Integer> choose;
	private JLabel points, ans1, ans2, ans3, ans4, qst, state ;
	private JButton quit ;
	private String questionStr;
	private String [] possibleAns;
	private int correctIndex, countPoint; // the index of the correct answer
	private Quiz quiz;
	
	// inner class for time listener
	private Timer timer = new Timer(DELAY, new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	        timeOver = true; // the time allocated for the question is ended
	    }
	}); //
	
		public Draw (Quiz quiz ) {
		
		this.quiz = quiz;
		Question qstion = quiz.randomQst(); // get random Question
		questionStr = qstion.getQuestion();
		possibleAns = qstion.getPossibleANS(); // get all answers
		correctIndex = qstion.getCorrectIndex() + 1; // get the correct index
		countPoint = 0;
		
		choose = new JComboBox<Integer> (options); // adding all the buttons
		points = new JLabel("points : " + countPoint);
		quit = new JButton("New Game");
		state = new JLabel("Starting the game");
		qst = new JLabel(questionStr);
		ans1 = new JLabel(possibleAns[0]);
		ans2 = new JLabel(possibleAns[1]);
		ans3 = new JLabel(possibleAns[2]);
		ans4 = new JLabel(possibleAns[3]);
		
		JPanel controls = new JPanel();	 // adding all the buttons and the labels to the north panel
		controls.add(points);
		controls.add(quit);
		controls.add(choose);
		controls.add(state);
		
		JPanel answers = new JPanel(); // the panel of the answers
		answers.add(qst);
		answers.add(ans1);
		answers.add(ans2);
		answers.add(ans3);
		answers.add(ans4);

		// layouts
		BoxLayout boxlayout = new BoxLayout(answers, BoxLayout.Y_AXIS);
		answers.setLayout(boxlayout);			
	    this.setLayout(new BorderLayout());	
	    
	    add(controls, BorderLayout.NORTH);
	    add(answers, BorderLayout.CENTER);

	    // event listeners
		choose.addItemListener(this);
		quit.addActionListener(this);
		
    	timeOver = false;
		timer.start(); // starts the timer

	}
		public void paintComponent (Graphics g) {
				super.paintComponent(g);
		}
		

		// if the state combo box is changed
	    public void itemStateChanged (ItemEvent e)
	    { 	
			
	    	if ( e.getStateChange() == ItemEvent.SELECTED  && ! choose.getSelectedItem().equals(0) ) { 
	    		
	    		int ans = ( (int) choose.getSelectedItem() ); // getting the chosen char
	    		
	    		if (ans == correctIndex && !timeOver)    {			
	    			countPoint += 10;
	    			state.setText("correct answer") ;
	    		}
	    		else if (ans != correctIndex  ) {
	    			countPoint -= 5;
	    			state.setText("wrong answer") ;	    			
	    		}
	    		else if  (timeOver) {
	    			countPoint -= 5;
	    			state.setText("time is over") ;	
	    		}
	    		this.reset();  	// new question to the screen	
	    	}       	    	
	    }
	    
	    // quitting the current game and creating a new game
		public void actionPerformed (ActionEvent e) {	
				state.setText("New game") ;
				this.newGame();
		}	
		

		// pop a new questions to the screen and resets the timer
		private void reset() {
			timer.stop(); 
			points.setText("points : " + countPoint);
			
			Question qstion = quiz.randomQst(); // get random Question
			if (qstion == null)	{	// the quiz ran out of questions to ask		
				this.endGame();
				return;
			}
			questionStr = qstion.getQuestion();
			possibleAns = qstion.getPossibleANS(); // get all answers
			correctIndex = qstion.getCorrectIndex() + 1; // get the correct index
			qst.setText(questionStr);
			ans1.setText(possibleAns[0]);
			ans2.setText(possibleAns[1]);
			ans3.setText(possibleAns[2]);
			ans4.setText(possibleAns[3]);
			choose.setSelectedIndex(0) ;
			
	    	timeOver = false;
			timer.start();
			
			repaint(); 
		}
		
		//we reached the end of the game - all possible questions have been asked
		private void endGame() {
			state.setText("All questions are over") ;
			this.newGame();
		}
		
		// stars new game
		private void newGame() {
			countPoint = 0; 	
			quiz.resetQuiz();
			this.reset(); 
		}
}