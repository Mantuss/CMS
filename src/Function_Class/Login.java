// package of the current class
package Function_Class;

// importing libraries
import java.sql.*;

public class Login {
	
	// declaring variables
	private String user,indi,pass;
	
	// class constructor with 3 parameters
    public Login(String user, String pass, String indi){
		
		this.user = user;
		this.pass = pass;
		this.indi = indi;
		
	}
    
    // method that checks whether the user exits or not and return a boolean value
    public boolean connection(){
    	
    	// exception handling 
    	try {
    		
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_management","root","");
	    	
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("SELECT * FROM logs where username='"+ this.user + "' AND password='"+ this.pass + "' AND indicator='"+indi+"' AND status='1'");
	    	
	    	// if the account exists it returns true
	    	if(rs.next()) {
	    		return true;
	    	}
	    	
	    	// if account does not exist it return false
	    	else {
	    		return false;
	    	}
    	}
    	
    	catch(Exception e) {
    		System.out.println("Failed");
    		return false;
    	}
	    	
    	
    }

}
