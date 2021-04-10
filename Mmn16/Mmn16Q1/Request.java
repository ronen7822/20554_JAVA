package Mmn16Q1;

//currency exchange request
public class Request {

	private  double currencySum;
	private String currencyFrom, currencyTo;
	
	// parse the string to the different fields of currencies
	public Request (String str) { 
		// all the logic of the parsing to the different fields
		int currPos =0 ; 
		int prevPos; 
		currencySum = Double.parseDouble (str.substring(0,(currPos = str.indexOf('"', currPos)))); 
		prevPos = currPos+1;
		currencyFrom = (str.substring(prevPos,(currPos = str.indexOf('"', currPos+1)))) ;
		prevPos = (currPos = str.indexOf('"', currPos+1));
		currencyTo = (str.substring(prevPos+1,(currPos = str.indexOf('"', currPos+1))));
	}
	
	// return the sum of money to convert
	public double getSum() {
		return currencySum;  
	}
	// return the currency to convert from
	public String getFrom() {
		return currencyFrom;
	}
	// return the currency to convert to
	public String getTo() {
		return currencyTo;
	}
}
