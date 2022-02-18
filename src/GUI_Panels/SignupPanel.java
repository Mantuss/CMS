// including the package of the current class
package GUI_Panels;

// importing libraries
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// importing Signup class from Function_Class package
import Function_Class.Signup;


/*  Signup panel is where the user creates his/her account. Signup Panel asks user for username and password. This panel will also redirect you to login page after your account
 *  has been created. You will also get a recovery code inorder to recover your account if you forgot your password at the time of login.
 */

public class SignupPanel implements ActionListener {
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private JFrame frame;
	private JButton login,signup;
	private JLabel label1, label2, label3;
	private JTextField username, recovery;
	private JPasswordField password;
	private String indi;
	
	// class constructor with 1 parameter
	public SignupPanel(String indi){
		
		// creating the frame for this panel 
		frame = new JFrame();
		
		// getting the icon for the frame
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		frame.setIconImage(frameIcon.getImage());
		frame.setTitle("Sign Up");
		frame.setSize(280,275);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		this.indi = indi;
		
	}
	
	// function to generate recovery code for the account
	private String randomCode() {
		
		int i=0;
		String code = "";
		
		// creating an object of class Random
		Random rnd = new Random();
		
		// while loop upto 6 to have a 6 character recovery code
		while(i < 6) {
			
			String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			
			// generating a random character
			char letter = abc.charAt(rnd.nextInt(abc.length()));
			
			// adding the character to the variable
			code += letter;
		
			// increment
			i+=1;
		}
		
		// returning the recovery code
		return code;
		
	}
	
	// method where all the components has been added to the panel
	public void signGUI() {
		
		// label for username
		label1 = new JLabel("Username:");
		label1.setBounds(11, 24, 80, 25);
		label1.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(label1);
		
		// textfield for username
		username = new JTextField();
		username.setBounds(92,20,160,30);
		username.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(username);
		
		// label for password
		label2 = new JLabel("Password:");
		label2.setBounds(12, 65, 80, 25);
		label2.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(label2);
		
		// passwordfield for password
		password = new JPasswordField();
		password.setBounds(92,62,160,30);
		password.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(password);
		
		// label for recovery code
		label3 = new JLabel("Recovery:");
		label3.setBounds(12 , 112, 80, 15);
		label3.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(label3);
		
		// textfield for recovery
		recovery = new JTextField();
		recovery.setBounds(92,105,160,30);
		recovery.setFont(new Font("Consolas",Font.PLAIN,15));
		recovery.setEditable(false);
		String code = randomCode();
		String rec_code = code;
		recovery.setText(rec_code);
		recovery.setHorizontalAlignment(JTextField.CENTER);
		frame.add(recovery);
		
		// signup button 
		signup = new JButton();
		
		// getting icon for the signup button
		ImageIcon signupIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\signup.png");
		signup.setIcon(signupIcon);
		signup.setText("SignUp");
		signup.setBounds(12,150,238,40);
		signup.addActionListener(this);
		signup.setFocusable(false);
		signup.setFont(new Font("Consolas",Font.PLAIN,14));
		frame.add(signup);
	
		// login panel button
		login = new JButton();
		login.setText("Already have an account?");
		login.addActionListener(this);
		login.setFont(new Font("Consolas",Font.PLAIN,13));
		login.setFocusable(false);
		login.setBounds(12, 197, 238, 30);
		frame.add(login);
	
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// if the signup button is pressed
		if(e.getSource() == signup) {
			
			// if the username's textfield and password's passwordfield is empty or its length is less than 6 
			if(username.getText().isBlank() || username.getText().length() < 6 && password.getText().isBlank() || password.getText().length() < 6) {
				
				// displaying the user an error message
				JLabel error = new JLabel("Oops.Something Went Wrong!");
				error.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,error,"Error !", JOptionPane.ERROR_MESSAGE);
				
				// clearing the username's textfield and password's passwordfield
				username.setText(null);
				password.setText(null);
				
			}
			
			// if the username and password is valid
			else {
				
				// creating object of Signup class
				Signup sig = new Signup(username.getText(),password.getText(),indi);
				boolean exist = sig.connect();
				
				// if the username already exists in the database
				if(exist) {
					JLabel error = new JLabel("User already exists!");
					error.setFont(new Font("Consolas",Font.PLAIN, 13));
					JOptionPane.showMessageDialog(null,error,"", JOptionPane.ERROR_MESSAGE);
					username.setText(null);
					password.setText(null);
				}
				
				// if there is no dublicate user, the account will be created
				else {
					
					boolean create = sig.createAccount(recovery.getText());
					
					// if the account is created a message is displayed to the user
					if(create) {
						
						// closing the signup frame and redirecting it to loginPanel
						frame.dispose();
						
						// showing the user an Information Message
						JLabel error = new JLabel("Account has been created");
						JLabel title = new JLabel("Signup Error");
						error.setFont(new Font("Consolas",Font.PLAIN, 13));
						JOptionPane.showMessageDialog(title,error,"", JOptionPane.INFORMATION_MESSAGE);
						LoginPanel log = new LoginPanel(this.indi);
						log.getUserInfo();
						
					}
					
				}
			 }
		}
		
		// if the login button is pressed then user is redirected to the login page
		if(e.getSource() == login) {
			
			// closing the signup panel frame
			frame.dispose();
			
			// creating a object of LoginPanel
			LoginPanel log = new LoginPanel(this.indi);
			
			// starting the login frame
			log.getUserInfo();
			
		}
	}
}
