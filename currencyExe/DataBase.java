package currencyExe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// an API to use the data base
public class DataBase {
	
	private static final String url = "jdbc:mysql://localhost:3306/currency";
	private static final String userName = "root";
	private static String password = "W2e3r4t5!";
	private static Connection con;
	private static Statement statement;
	private static final String ENCRYPTION_KEY = "128cu3y1n~?" ;
	
	
	// //establish connection to the data base
	public static void establishConnection ()  {

		try {
			con = DriverManager.getConnection(url, userName, password);
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
		
		// encrypt the password to store in the data base
		String encryptedPassword = Encrypting.encrypt(password, ENCRYPTION_KEY);
		System.out.println("encryptedPassword to the data base: "+encryptedPassword);
		
		updateQuery ( "INSERT INTO users VALUES (" + id +" , '"+ name +"' , 'ILS' , 0) ;" );
		updateQuery ( "INSERT INTO passwords VALUES (" + id +" , '"+ encryptedPassword +"' , False ) ;" );		
	}
	
	
	// add doc 
	public static void addCurrencyToUser (int id, double sum, String currency) {
		
		try {
			String query =  " SELECT sum  FROM users  WHERE  currency = '" +currency+ "' AND person_id = " + id + " ;" ;
			ResultSet result = statement.executeQuery(query);
		
			// add doc
			if (result.next()) {
				double totalSum = result.getDouble(1) + sum;
				// update the data base
				updateQuery ( " UPDATE  users SET sum = " + totalSum + "  WHERE  currency = '" +currency+ "' And person_id = " + id + ";" ) ;
			}
			else {
				updateQuery ( "INSERT INTO users VALUES (" + id +" , 'default_name' , '" + currency + "'  , " + sum + ") ;" );
			}
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// return true if the password matches the password in the data base
	public static boolean correctPassword (int id, String password ) {
		
		try {
			String query =  " SELECT password  FROM  passwords  WHERE  id = " +id+ " ;" ;
			ResultSet result = statement.executeQuery(query);
			
			// if there is no result for the query
			if ( result.next() == false)
				return false ;
			
			// decrypt the password from the data base
			String encryptedPassword =  result.getString(1);
			String decryptedPassword  = Encrypting.decrypt(encryptedPassword, ENCRYPTION_KEY);
			// if the passwords are equal return true, false otherwise
			if(decryptedPassword.equals(password))
				 return true;
			return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	// lock other users from singing in
	public static  void  lockUser(int idNum, String passwordText) {	
		
		password = Encrypting.encrypt(passwordText, ENCRYPTION_KEY);
		updateQuery ( " UPDATE passwords SET loged_in = True    WHERE  password = '" +password+ "' And id = " + idNum + ";" ) ;
	}
	
	
	// unlock the user 
	public static  void  unlockUser(int idNum, String passwordText) {	
		password = Encrypting.encrypt(passwordText, ENCRYPTION_KEY);
		updateQuery ( " UPDATE passwords SET loged_in = False    WHERE  password = '" +password+ "' And id = " + idNum + ";" ) ;
	}
	
	
	// unlock all users in the data base
	public static void unlockAllUsers() {
		updateQuery ( " UPDATE passwords SET loged_in = False ;" ) ;
	}
	
	
	// check whether the user is already logged in
	public static boolean userIsLogged (int idNum, String passwordText) {
		
		password = Encrypting.encrypt(passwordText, ENCRYPTION_KEY);
		try {
			String query =  " SELECT loged_in  FROM passwords  WHERE  password = '" +password+ "' And id = " + idNum + ";" ;
			System.out.println("query: "+ query);
			ResultSet result = statement.executeQuery(query);
			result.next() ;
			return result.getBoolean(1);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	// returns the total Net worth of the user from the data base
	public static double getTotalValue(int idNum) {
			 
		try {
			String query =   " SELECT SUM(sum * currency_rate) AS TotalItemsOrdered"
					+ "  from users, ExchangeRare   where person_id = " + idNum  + " and currency = currency_name; " ;
			System.out.println("query: "+ query);
			ResultSet result = statement.executeQuery(query);
			result.next() ;
			return result.getDouble(1);
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	// update the data base with the given query
	private static void updateQuery (String query) {
		try {
			statement.executeUpdate(query);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}