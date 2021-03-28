package Mmn13Q2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFromFile {
	
	private final int MAX_LENGTH = 1000;
	private String [] lines; // all the lines
	private int length;

	// read line from dedicated file
	public ReadFromFile(String filename) {	
		
		lines = new String [MAX_LENGTH] ;
		int i = 0;
		try {
			  Scanner input = new Scanner(new File(filename)); 
			  
			  while ( input.hasNextLine() && i < MAX_LENGTH) {// we assume no more than 1000 lines in a given file
				  lines [i] = input.nextLine();
				  i++;
			  }
			  input.close(); // close the file
			}  
			catch (IOException e) {
				System.out.println("Error accessing input file!");
			}
		length = i;	
	}
	
	// return all the lines from the file
	public String [] getLines() {
		return lines;
	}
	
	// return the amount of lines in the buffer
	public int getLength() {
		return length;
	}
	
}
