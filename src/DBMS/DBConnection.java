// package of the current class file
package DBMS;

// importing libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
	
	// declaring private variables
	private Connection connection;
	private Statement statement;
	
	// function to get connection from the database
	private Statement connect() {
		
		// exception handling
		try {
			
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_management","root","");
	    	statement = connection.createStatement();
	    	
	    	// returning the statement
	    	return statement;
    	}
    	
    	catch(Exception e) {
    		
    		return statement;
    	}
		
	}
	
	// getter function which returns statement the function is called	
	public Statement getConnection() {
		
		// creating object of DBConnection
		DBConnection connection = new DBConnection();
		Statement statement = connection.connect();
		
		// returning the statement
		return statement;
			
	}
		
		
}