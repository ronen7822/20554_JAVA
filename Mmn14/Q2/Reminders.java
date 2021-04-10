package Mmn14Q2;

import java.util.Hashtable;

public class Reminders {
	
	private Hashtable<Date, String> remindersTable;
	
	// constructor for Reminders
	public Reminders() {
		remindersTable = new Hashtable<Date, String>();
	}
	
	// add new reminder to the table
	public void addReminder(Date date, String str) {
		remindersTable.put(date,str);
	}
	
	// return the string linkeD to the hashDate
	public String getReminder(Date hashDate) {
		return remindersTable.get(hashDate);
	}
	
	public Hashtable<Date, String> getHashTable () {
		return remindersTable;
	}

}
