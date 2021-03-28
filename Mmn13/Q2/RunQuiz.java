package Mmn13Q2;

import javax.swing.JFrame;

public class RunQuiz {

	// main method for the quiz
	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame ("test"); // straight forward , building the main frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		Quiz quiz = new Quiz();
		Draw panel = new Draw( quiz );		
		frame.add(panel);		
		frame.setVisible(true);

	}
}
