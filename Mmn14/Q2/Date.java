package Mmn14Q2;

import java.io.Serializable;

// date in a calendar consists of year month and day
public class Date implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final int MAX_DAYS_IN_MONTH = 31;
	private final int MAX_DAYS_IN_FEB = 29;
	private final int FEB = 2;
	private final int MAX_MONTH = 12;
	
    private int month, day, year;
    
    // constructor for new Date
    public Date(int year, int month, int day ) {
    	
    	if (month > 0 && month <= MAX_MONTH)
    		this.month = month; 
    	else    System.out.println("undifined month : "+month);
    		
    	this.year = year; 
    	
    	// i delibertely ignore leap years 
    	if ((day > 0 && day <= MAX_DAYS_IN_MONTH && month != FEB ) || ( month == FEB && day > 0 && day <= MAX_DAYS_IN_FEB ) )
    		this.day = day;
    	else 	System.out.println("undifined day : "+day +" in month: " +month);
    }
    
    // overriding equals method to compare 2 dates
    public boolean equals(Object other) {
    	
		if ( ! (other instanceof Date) ) {
			System.out.println("Eror - comparing 2 diffrent types of objects");
			return false;
		}
		// else they are from same type
		Date temp = (Date) other;
		return (year == temp.getYear() && month == temp.getMonth() && day == temp.getDay() ) ;  	
    }
    
    // change this later to better has code
    public int hashCode()
    {
        return year*day/month;
    }
    
    // return the year
    public int getYear() {
    	return this.year;
    }
    // return the month
    public int getMonth() {
    	return this.month;
    }
    // return the day
    public int getDay() {
    	return this.day;
    }
    public String toString() {
    	return "year:" + year + "\tmonth:" + month + "\tday:" + day ;
    }

}
