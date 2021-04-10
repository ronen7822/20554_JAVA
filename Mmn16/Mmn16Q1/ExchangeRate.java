package Mmn16Q1;

import java.util.HashMap;

public class ExchangeRate {
	
	private HashMap<String, Double> exchangeTable = new HashMap<String, Double>();
	
	// initiate the exchange Table
	public ExchangeRate ()  {
		exchangeTable.put("ILS", 1.0);
		exchangeTable.put("EUR", 3.92);
		exchangeTable.put("USD", 3.29);
		exchangeTable.put("CNY", 0.50);
		exchangeTable.put("CAD", 2.61);						 
	}
	
	// return the conversion of currencyFrom to currencyTo multiplied by the sum
	public double rate(double sum, String currencyFrom, String currencyTo) { 
		return sum * (exchangeTable.get(currencyFrom) / exchangeTable.get(currencyTo)) ;
	}
	
}
