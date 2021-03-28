package Mmn13Qst1;

public class HangMan {
	 
	// reservoir of words 
	private static String [] vocabulary = new String [1000] ; // all the words the game chooses from
	
	private final int lenOfAlphabet = 26;
	private int count, len ; // wrong guess counter
	private int [] letters ;
	private String chosenWord ;
	private char guess [];
	
	// initiating the hang man with word to guess
	@SuppressWarnings("resource")
	public HangMan() {			
		ReadWords scan = new ReadWords("./src/Mmn13Qst1/text.txt");		// reads the words from the file	
		len = scan.getLength() ; // the number of the words we randomly choosing from
		vocabulary = scan.getVac();
		
		selectWord(); // selecting a new word	
	}
	
	// selecting anew word and resetting the counter
	private void selectWord () {
		count = 0;
		
		letters = new int[lenOfAlphabet]; // array to store used letters, initiated to 0's
		for (int i = 0; i < letters.length; i++)
			letters[i] = 0;
		
		// the random word in hang man		
		chosenWord =  vocabulary[ (int)( Math.random() * len ) ] ;
		System.out.println("for cheaters the chosen word is: "+ chosenWord);
		
		guess = new char[chosenWord.length()] ; // creating the guess word
		for (int i = 0; i < chosenWord.length(); i++)
			guess[i] = '_' ;
	}
	
	// guessing a character in the chosen word
	public void guessLetter(char ch) {	
		
		if (! finished() ) {						
			// if the character is not from the alphabet or it already have been chosen
			if ( ch -'a' >= lenOfAlphabet ||  ch -'a' < 0 || letters [ch -'a'] == 1) 		
				return;
			
			letters [ch -'a'] = 1;	// insert 1 to the holding place in the array			
			
			// if the char appears in the chosen word we put the characters in the guess
			if ( chosenWord.indexOf(ch) >= 0 ) {
				for (int i = 0; i < chosenWord.length(); i++) {
					if (chosenWord.charAt(i) == ch)					
						guess[i] = ch ;
				}		
			}
			else
				count ++;	 // incrementing the counter of wrong guess
		}
	}		
	
	// return true if all the chars in the array are not equal to '_' aka if we completed the game
	private static boolean complete(char [] array) {
		for (int i = 0; i < array.length; i++) 
			if ( array[i] == '_' )
				return false ;
		return true ;
	}
	
	// return true if we finished else false
	public boolean finished () {
		return complete(guess);
	}
	
	public int getCount () {
		return count;
	}
	public void setCount(int count) {
		this.count = count;	
	}
	
	// shows the word guessed so far
	public String showSoFar () {
		String str = new String(guess);
		return str;
	}
	
	// shows the chosen word
	public String showord() {
		return this.chosenWord;
	}
	
	// reset the hang man
	public void reset() {
		this.selectWord();
	}
	
	// return true if the character is allready chosen
	public boolean allreadyChosen(char ch) {
		if (letters [ch -'a'] == 1)	
			return true ;
		return false ;
	}			
}

	

