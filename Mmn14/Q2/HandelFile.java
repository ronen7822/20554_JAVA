package Mmn14Q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

// Handles any Dealing with files needed for the main method
public class HandelFile {
	
	File currentDir;
	
	public HandelFile () {
		// open exiting file
		if (JOptionPane.showConfirmDialog(null, "Would you like to load data from file?","Open File",JOptionPane.YES_NO_OPTION) == 0) 
			currentDir = this.getFile();
		// if the user open and then cose cancel
		if (currentDir == null)   	
			currentDir = new File("text.txt"); // opens new file			

	}
	// return the file to read from
	private File getFile() {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		return fc.getSelectedFile();
	}
	
	// save to file the reminders
	public void saveAll( Hashtable<Date,String> table) throws FileNotFoundException, IOException  {
		ObjectOutputStream out = new ObjectOutputStream
				(new FileOutputStream(currentDir));
		// adding all the memos to the file
		table.forEach((k,v)->{
				try {
					out.writeObject(v); 
				}
				catch (IOException e) {
				}
				
		});
		out.close();
	}
	

}






	
	
