// package of the current class
package Function_Class;

// importing the libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Signup {
	
	// declaring the variables
	private String user,pass,indi;
	
	// class constructor with 3 parameters
	public Signup(String user, String pass,String indi){
		
		this.user = user;
		this.pass = pass;
		this.indi = indi;
	}
	
	// function to create an account
	public boolean createAccount(String recovery) {
		
		// exception handling
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_management","root","");
	    	
	    	Statement st = con.createStatement();
	    	
	    	// executing the query to create an account
	    	st.executeUpdate("INSERT INTO `logs`(`username`, `password`, `indicator`,`recovery_code`,`status`) VALUES ('"+ this.user +"','"+this.pass+"','"+this.indi+"','"+ recovery +"','1')");
			return true;
		}
		
		catch(Exception e) {
			
			return false;
		}
		
		
	}
	
	
	// function to check whether the account already exists or not
	public boolean connect()  {
		
		// exception handling
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_management","root","");
	    	
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("SELECT * FROM logs where username='"+ this.user + "'");
	    	
	    	// if the account exists then return true
	    	if(rs.next()) {
	    		
	    		return true;
	    		
	    	}
	    	
	    	// else return false
	    	return false;
	    		

		}
		
		catch(Exception e) {
			
			return false;
			
		}
	}
}
