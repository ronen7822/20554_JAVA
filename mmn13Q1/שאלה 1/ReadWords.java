package Mmn13Qst1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// reads words form a given file and store them in an array
public class ReadWords {
	
	private final int MAX_LENGTH = 1000;
	private String [] vocabulary;
	private int length;
	
	// implement this method
	public ReadWords(String filename) {	
		
		vocabulary = new String [MAX_LENGTH] ;
		int i = 0;
		
		try {
			  File myFile = new File(filename);
			  Scanner s = new Scanner(myFile); 
			  
			  while (s.hasNext() && i < MAX_LENGTH) {// we assume no more than 1000 words in a given file
				  vocabulary [i] = s.next();
				  i++;
			  }
			  s.close(); // close the file
			} 
			catch (IOException e) {
				System.out.println("Error accessing input file!");
			}
	
		length = i;	
	}
	
	// return the vocabulary read from the file
	public String []getVac() {
		return vocabulary;
	}
	
	// return the amount of words in the vocabulary
	public int getLength() {
		return length;
	}
}
