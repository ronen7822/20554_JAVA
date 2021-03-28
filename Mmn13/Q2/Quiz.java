package Mmn13Q2;

public class Quiz {
	
	private int numOfQst;
	private final int MAX_QST = 200; // max number of questions
	private Question questions[];
	private String [] lines; 
	private boolean [] alReadyAsked; // holds true if the question in the index i is already have been asked
	
	// creating a new quiz object
	public Quiz() { 
		questions = new Question [MAX_QST] ;
		
		ReadFromFile reader = new ReadFromFile("./src/Mmn13Q2/file.txt");// reads the lines from the file
		lines = reader.getLines();
		
		int i = 0;
		numOfQst  = 0;
		while (i < reader.getLength()) {
		
			String[] temp = new String[5] ; // preparing the temporary array in order to create new question
			for (int j = 0; j < 5 ; j++, i++ ) 
				temp[j] = lines[i] ;
	
			questions [numOfQst++] = new Question (temp) ; // creating new question			
		}
		
		alReadyAsked = new boolean [numOfQst]; // Initiating the array
		for (i=0; i<numOfQst; i++)	
			alReadyAsked[i] = false;
 
	}
	
	// return a random question that not been asked yet or null if all the questions have been asked 
	public Question randomQst() {
		int randIndx = (int)( Math.random() * numOfQst ) ;
		
		if ( ! alReadyAsked[randIndx] ) {		
			alReadyAsked[randIndx] = true;
			return questions[randIndx] ;
		}
		// else asked another question
		
		for ( int i = (randIndx+1) % (numOfQst-1); i != randIndx ;) {// Iterate over the the array in  tzickly way
			if (! alReadyAsked[i]) {
				alReadyAsked[i]= true;
				return questions[i]; 
			}
			 i++ ;
			 i %= (numOfQst-1) ;
				
		}
			return null; // if all the question been asked return null
	}
	
	// reset the quiz.  questions have not been asked yet
	public void  resetQuiz () {
		for (int i=0; i<numOfQst; i++)	
			alReadyAsked[i] = false;
	}
	

}
