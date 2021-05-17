package currencyExe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// an api to use the data base
public class DataBase {
	
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
		updateQuery ( "INSERT INTO ExchangeRare VALUES (" +'"'+ currency +'"'+ " , "+ rate +")" ) ;
	}
	
	// remove currency from the data base
	public static void removeCurrency(String currency) {
		updateQuery ( "DELETE FROM ExchangeRare   WHERE currency_name = '" +currency+ "' ;" ) ;
	}
	
	// return whether the currency is already in the data base
	public static boolean currencyExistInTable(String key) {		
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
	
	// return true if the user id is already in the data base
	public static boolean userExsit(String key) {
		try {
			String query =  " SELECT person_id  FROM users  WHERE  person_id = '" +key+ "' ;" ;
			ResultSet result = statement.executeQuery(query);
			return result.next() ;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	  
	// adding the user to the data base
	public static void addUser(int id, String password, String name) {
		
		// encrypt the password in the data base!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		updateQuery ( "INSERT INTO users VALUES (" + id +" , '"+ name +"' , 'ILS' , 0) ;" );
		updateQuery ( "INSERT INTO passwords VALUES (" + id +" , '"+ password +"' ) ;" );		
	}
	
	// return true if the password matches the password in the data base
	public static boolean correctPassword (int id, String password ) {
		
		try {
			String query =  " SELECT password  FROM  passwords  WHERE  id = " +id+ " ;" ;
			System.out.println(query);
			ResultSet result = statement.executeQuery(query);
			// if there is no result for the query
			if ( result.next() == false)
				return false ;
			
			// if the passwords are equal return true, false otherwise
			if(result.getString(1).equals(password))
				 return true;
			return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	// update the data base with the given query
	private static void updateQuery (String query) {

		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}