package currencyExe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// an api to use the data base
public class ExchangeRate {
	
	private static String url = "jdbc:mysql://localhost:3306/currency";
	private static String userName = "root";
	private static String password = "W2e3r4t5!";
	private static Connection con;
	private static Statement statement;
	
	
	// //establish connection to the data base
	public static void establishConnection ()  {

		try {
			con = DriverManager.getConnection(url,userName,password);
			statement = con.createStatement();
		}
		catch(SQLException e) {
			System.out.println("faild to connect the data base");
			e.printStackTrace();
		}
	}
	
	// close connection with data base
	public static void closeConnection() {
		try {
			statement.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("faild to close the data base");
			e.printStackTrace();
		}
	}
	
	// return the conversion of currencyFrom to currencyTo multiplied by the sum
	public static double rate (double sum, String currencyFrom, String currencyTo) { 
		String fromQuery =  " SELECT currency_rate FROM  ExchangeRare WHERE  currency_name = '" +currencyFrom+ "' ;" ;
		String toQuery = " SELECT currency_rate FROM  ExchangeRare WHERE  currency_name = '" +currencyTo+ "' ;" ;
		
		try {
			// get results from data base
			ResultSet result = statement.executeQuery(fromQuery);
			result.next();
			double fromRate = result.getDouble(1);
			
			result = statement.executeQuery(toQuery);
			result.next();
			double toRate = result.getDouble(1);
			
			return sum * fromRate /toRate ;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// unreachable code just for the compiler
		return 0;			
	}
	
	// adds new currency to the data base relative to the shekel rate
	public static void addCurrency(String currency, double rate) {
		String insertQuery = "INSERT INTO ExchangeRare VALUES (" +'"'+ currency +'"'+ " , "+ rate +")" ;
		try { // adds the values to the data base
			statement.executeUpdate(insertQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// remove currency from the data base
	public static void removeCurrency(String currency) {
		String removeQuery = "DELETE FROM ExchangeRare   WHERE currency_name = '" +currency+ "' ;" ;
		try { // adds the values to the data base
			statement.executeUpdate(removeQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// return whether the currency is already in the data base
	public static boolean existInTable(String key) {		
		try {
			String query =  " SELECT currency_rate FROM  ExchangeRare WHERE  currency_name = '" +key+ "' ;" ;
			ResultSet result = statement.executeQuery(query);
			return result.next() ;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// return all Currencies in the data base
	public static String[] getCurrencies() {
		
		try {
			String query = "SELECT currency_name FROM ExchangeRare";
			String getSize = "SELECT count(*) FROM ExchangeRare" ;
			
			// number of rows in the data base			
			ResultSet count = statement.executeQuery(getSize);
			count.next();
			int rowsNum = count.getInt(1);

			ResultSet result = statement.executeQuery(query);
			String arr[] = new String[rowsNum];
			int i = 0;
			while(result.next()) 
				arr[i++] = result.getString(1);
						
			return arr;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}