package Mmn13Q2;

import java.util.ArrayList;
import java.util.Collections;

public class Question {
	
	private ArrayList<String> answers;
	private String rightAns, question ;
	private final int MAX_LENGTH = 5; // Question and 4 answers
	
	// possible question
	public Question(String arr[]) {
		answers = new ArrayList<String>() ;
		question = arr[0];
		rightAns = arr[1];		 
		for (int i = 1; i < MAX_LENGTH; i++ )
			answers.add(arr[i]) ;
		
		Collections.shuffle(answers); // SHUFFLE THE ANSWERS
	}
	
	// return the question
	public String getQuestion() {
		return question;
	}
	
	// return the i answer
	public String getSomeAnswer (int index) {
		if ( index <= 3 && index >=0)	
			return answers.get(index);
		return "EROR";
	}
	// return array of all possible answers
	public String[] getPossibleANS() {
		String[] arr = new String[answers.size()];
        for (int i = 0; i < answers.size(); i++)
            arr[i] = answers.get(i);

		return arr ;
		
	}
	// return the right answer
	public String getRightAnswer () {
		return rightAns;
	}
	// return the index of the right answer
	public int getCorrectIndex() {
		return answers.lastIndexOf(rightAns);
	}
	
}
