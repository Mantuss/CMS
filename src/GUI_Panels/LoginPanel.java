// package of the LoginPanel class
package GUI_Panels;

//importing Database manage file from Function_Class Package
import Function_Class.Login;

//importing libraries
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*  Login panel is where the user logs into his/her account. Login Panel asks user for username and password. This panel will also redirect you to signup,
 *  if you don't have an account. You can also recovery your account from this Panel but inorder to recover you need to get the recovery code given to you
 *  at the time of signup.
 */


public class LoginPanel implements ActionListener {
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private JFrame frame;
	private JButton login,signup, forgot, back;
	private JLabel label1, label2;
	private JTextField username;
	private JPasswordField password;
	private String indi;
	
	// constructor of this class
	public LoginPanel(String indi){
	
		this.indi = indi;
		
	  }
	
	// checkDatabase Function which checks whether the username is valid or not
	private boolean checkDatabase(String user)  {
		
		// exception handling
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_management","root","");
	    	
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("SELECT * FROM user_info WHERE username='"+ user + "'");
	    	
	    	// if the user exits return true
	    	if(rs.next()) {
	    		
	    		return true;
	    		
	    	}
	    	
	    	// if the user does not exit return false
	    	else {
	    		
	    		return false;
	    	}
			
		}
		
		catch(Exception e){
			
			return false;
		}
		
		
	}
	
	// starting the frame of the panel
	public void getUserInfo() {
		
		// main frame of Loginpanel
		frame = new JFrame();
		frame.setSize(280,260);
		frame.setTitle("Login");
		
		// getting the icon for the frame
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		frame.setIconImage(frameIcon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		
		// label for username
		label1 = new JLabel("Username:");
		label1.setBounds(11, 49, 80, 25);
		label1.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(label1);
		
		// texfield for the username
		username = new JTextField();
		username.setBounds(92,45,160,30);
		username.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(username);
		
		// label for password
		label2 = new JLabel("Password:");
		label2.setBounds(12, 90, 80, 25);
		label2.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(label2);
		
		// password field for the password
		password = new JPasswordField();
		password.setBounds(92,87,160,30);
		password.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(password);
		
		// login button
		login = new JButton();
		login.setText("Login");
		
		// getting the icon for login button
		ImageIcon loginIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\login.png");
		login.setIcon(loginIcon);
		login.setBounds(12,133,238,40);
		login.addActionListener(this);
		login.setFocusable(false);
		login.setFont(new Font("Consolas",Font.PLAIN,14));
		frame.add(login);
		
		// signup button
		signup = new JButton();
		signup.setText("SignUp");
		signup.addActionListener(this);
		signup.setFont(new Font("Consolas",Font.PLAIN,13));
		signup.setFocusable(false);
		signup.setBounds(12, 180, 80, 30);
		
		// forgot password
		forgot  = new JButton();
		forgot.setText("Forgot Password?");
		forgot.addActionListener(this);
		forgot.setFocusable(false);
		forgot.setFont(new Font("Consolas",Font.PLAIN,13));
		
		
		// if the user is either student or teacher/ instructor changing the size of the forgot button
		if(this.indi == "Student" || this.indi == "Teacher") {
			
			frame.setLocationRelativeTo(null);
			forgot.setBounds(100, 180, 150, 30);
			frame.add(signup);
			frame.add(forgot);
			
		}
		
		// if the user is admin changing the size of the forgot button
		else {
			forgot.setBounds(12, 180, 238, 30);
			frame.add(forgot);
		}
		
		
		// back button of the loginpanel which will redirect you to the mainpanel
		
		// getting the icon for back button
		Icon exitIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\exit.png");
		back = new JButton();
		back.setIcon(exitIcon);
		back.setBounds(10, 6, 50, 25);
		back.addActionListener(this);
		back.setFocusable(false);
		back.setFont(new Font("Consolas",Font.PLAIN,14));
		
		frame.add(back);
		frame.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// if the login  button is pressed
		if(e.getSource() == login) {
			
			@SuppressWarnings("deprecation")
			
			// creating an object of Login class from Function class package
			Login obj = new Login(username.getText(),password.getText(),this.indi);
			
			// checking if the account exists or not
			boolean exist = obj.connection();
			
			// if account exits in the database
			if(exist) {
				
				// if the indicator is student we need to redirect the user to the student panel
				if(this.indi == "Student") {
					
					// disposing the Loginpanel inorder to open Student panel
					frame.dispose();
					
					// checking if the user has already provided every information to set up his/her profile
					if(checkDatabase(username.getText())) {
						
						// making object of StudentPanel
						StudentPanel student = new StudentPanel(username.getText());
						
						// exception handling
						try {
							
							student.startGUI();
						} 
						
						catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					// if there is no record of information of the user then making the user set up his profile
					else {
						
						// making object of ProfileSetPanel
						ProfileSetPanel profile = new ProfileSetPanel(this.indi, username.getText());
						try {
							
							// starting the profile Setup GUI
							profile.profileGUI();
							
						} 
						
						catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					}
					
			
				}
				
				
				// if the indicator is student we need to redirect the user to the teacher panel
				if(this.indi == "Teacher") {
					
					// disposing the Loginpanel inorder to open Teacher Panel
					frame.dispose();
					
					// checking if the user has already provided every information to set up his/her profile
					if(checkDatabase(username.getText())) {
						
						// creating object of InstructorPanel
						InstructorPanel instructor = new InstructorPanel(username.getText()) ;
						instructor.startGUI();
						
					}
					
					// if there is no record of information of the user then making the user set up his profile
					else {
						
						// making object of ProfileSetPanel
						ProfileSetPanel profile = new ProfileSetPanel(this.indi, username.getText());
						
						// exception handling
						try {
							
							profile.profileGUI();
						} 
						
						catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
						}
					
					}

				}
				
				
				// if the indicator is student we need to redirect the user to the teacher panel
				if(this.indi == "Admin"){
					
					// disposing the Loginpanel inorder to open Teacher Panel
					frame.dispose();
					
					// creating object of Admin Panel
					AdminPanel admin = new AdminPanel(username.getText());
					
					// starting the admin frame
					admin.startGUI();
					
					
				}
	
			}
			
			
			// if account is not found error message will be shown to the user
			else {
				
				// clearing text from the textfield and passwordfield
				username.setText(null);
				password.setText(null);
				
				// displaying a error message to the user
				JLabel error = new JLabel("Oops.Something Went Wrong!");
				error.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,error,"", JOptionPane.ERROR_MESSAGE);
			}
												
		}
		
		// if the signup button is pressed
		if(e.getSource() == signup) {
			
			// closing the login frame and opening the signupframe
			frame.dispose();
			
			// creating object of Signup Panel
			SignupPanel obj = new SignupPanel(this.indi);
			
			// starting the Signup frame
			obj.signGUI();
			
		}
		
		// if the forgot password button is pressed
		if(e.getSource() == forgot) {
			
			// closing the login frame and opening the forgot password frame
			frame.dispose();
			
			// calling ForgotPasswordPanel constructor
			new ForgotPasswordPanel(this.indi);
			
		}
		
		// if the back button is pressed
		if(e.getSource() == back) {
			
			// closing the login frame and opening the MainPanel frame
			frame.dispose();
			
			// calling MainPanel() constructor and startGUI() method
			new MainPanel().startGUI();
			
		}
		
	}

}
