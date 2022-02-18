// package of the current class
package GUI_Panels;

// importing Database Management file from DBMS package 
import DBMS.DatabaseManage;

// importing libraries
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;


// ForgotPasswordPanel helps a user to recover his/her accounts password with the help of a recovery code. User will get to change their password with the help of the recover code

@SuppressWarnings("serial")
public class ForgotPasswordPanel extends JFrame implements ActionListener {
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private String indi;
	private JPanel backPanel;
	private JTextField recoveryCode, userName;
	private JPasswordField password, confirm;
	private JButton searchAccount, back, saveChange;
	private JLabel username, newPassword, confirmPassword;
	

	// class constructor with 1 parameter
	public ForgotPasswordPanel(String indi) {
		
		// border for components
		Border line = BorderFactory.createLineBorder(Color.GRAY);
		
		this.indi = indi;
		
		// frame for ForgotPasswordPanel
		this.setTitle("Forgot Password");
		this.setSize(450,295);
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		this.setIconImage(frameIcon.getImage());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// panel with textfield and password field
		backPanel = new JPanel();
		backPanel.setBounds(20,15, 250, 220);
		backPanel.setBackground(Color.WHITE);
		backPanel.setBorder(line);
		backPanel.setLayout(null);
		this.add(backPanel);
		
		// textfield for recovery code
		recoveryCode = new JTextField();
		recoveryCode.setBounds(280, 15, 140, 30);
		recoveryCode.setFont(new Font("Consolas",Font.PLAIN,14));
		this.add(recoveryCode);
		
		// search button to search user account
		searchAccount = new JButton("Search");
		searchAccount.setBounds(280, 55, 140, 30);
		ImageIcon searchIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\search.png");
		searchAccount.setIcon(searchIcon);
		searchAccount.addActionListener(this);
		searchAccount.setFocusable(false);
		searchAccount.setFont(new Font("Consolas",Font.PLAIN,14));
		this.add(searchAccount);
		
		// button to save the password change
		saveChange = new JButton("Save Change");
		saveChange.setBounds(280, 165, 140, 30);
		saveChange.addActionListener(this);
		saveChange.setFocusable(false);
		saveChange.setEnabled(false);
		saveChange.setFont(new Font("Consolas",Font.PLAIN,14));
		this.add(saveChange);
		
		// back button which redirects user back to login panel
		back = new JButton();
		back.setBounds(280, 205, 140, 30);
		Icon exitIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\exit.png");
		back.setIcon(exitIcon);
		back.setFocusable(false);
		back.addActionListener(this);
		back.setFont(new Font("Consolas",Font.PLAIN,14));
		this.add(back);
		
		// Label for username
		username = new JLabel("Username: ");
		username.setBounds(10, 5, 150, 30);
		username.setFont(new Font("Consolas",Font.PLAIN,14));
		backPanel.add(username);
		
		// textfield for username
		userName = new JTextField();
		userName.setBounds(10, 35, 230, 30);
		userName.setFont(new Font("Consolas",Font.PLAIN,14));
		userName.setEditable(false);
		backPanel.add(userName);
		
		// label for new password
		newPassword = new JLabel("New Password: ");
		newPassword.setBounds(10, 75, 150, 30);
		newPassword.setFont(new Font("Consolas",Font.PLAIN,14));
		backPanel.add(newPassword);
		
		// passwordfield for new password
		password = new JPasswordField();
		password.setBounds(10, 105, 230, 30);
		password.setFont(new Font("Consolas",Font.PLAIN,14));
		password.setEditable(false);
		backPanel.add(password);
		
		// label for confirmpassword
		confirmPassword = new JLabel("Confirm Password: ");
		confirmPassword.setBounds(10, 145, 150, 30);
		confirmPassword.setFont(new Font("Consolas",Font.PLAIN,14));
		backPanel.add(confirmPassword);
		
		// passwordfield for confirm password
		confirm = new JPasswordField();
		confirm.setBounds(10, 175, 230, 30);
		confirm.setFont(new Font("Consolas",Font.PLAIN,14));
		confirm.setEditable(false);
		backPanel.add(confirm);
		
		// setting the frame layout to null
		this.setLayout(null);
		
		// making the frame visible so that user can visualize it 
		this.setVisible(true);
		
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// creating object of DatabaseManage
		DatabaseManage manage = new DatabaseManage();
		
		// if the search button is pressed
		if(e.getSource() == searchAccount) {
			
			// if the recovery textfield is empty then error is displayed to the user
			if(recoveryCode.getText().isBlank() || recoveryCode.getText().isEmpty() || recoveryCode.getText().length() < 6) {
	
				JLabel er = new JLabel("Invalid Search !");
				er.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,er,"Error.", JOptionPane.ERROR_MESSAGE);
				
			}
			
			// if the recovery text field is not empty
			else {
				
				// declaring and initializing a variable 
				String username = null;
				
				// exception handling
				try {
					
					username = manage.getUserInfo("SELECT username FROM logs WHERE recovery_code='"+ recoveryCode.getText() +"'");
				} 
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
				finally {
				
					// enabling the save change button and field for changing the password
					userName.setText(username);
					password.setEditable(true);
					confirm.setEditable(true);
					saveChange.setEnabled(true);
					recoveryCode.setText("");
					
				}
	
			}

		}
		
		// if save change button is pressed
		if(e.getSource() == saveChange) {
			
			// if the username texfield and password's passwordfield or the characters is less than 6 then error is displayed to the user
			if(userName.getText().isBlank() || userName.getText().isEmpty() || password.getText().isBlank() || password.getText().isEmpty() ||  password.getText().length() < 6  ) {
				
				// displaying error to the user
				JLabel er = new JLabel("Invalid Information !");
				er.setFont(new Font("Consolas",Font.PLAIN, 13));
				JOptionPane.showMessageDialog(null,er,"Error.", JOptionPane.ERROR_MESSAGE);
				
			}
			
			// if the provided inforamtion is valid
			else {
				
				// checking whether new password and confirm password is same
				if(confirm.getText().equals(password.getText())) {
					
					// exception handling
					try {
						
						manage.updateLogs("UPDATE logs SET password='"+ password.getText() +"' WHERE username = '"+ userName.getText() +"' ");
					} 
					
					catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					finally {
						
						// diplaying use that their password has been changed
						JLabel er = new JLabel("Password has been changed! ");
						er.setFont(new Font("Consolas",Font.PLAIN, 13));
						JOptionPane.showMessageDialog(null,er,"", JOptionPane.INFORMATION_MESSAGE);
						
						// disposing the forgot password panel and redirecting to Login Panel
						this.dispose();
						new LoginPanel(this.indi).getUserInfo();
						
					}

				}
				
				// if confirm password and new password does not match
				else {
					
					// displaying error to the user
					JLabel er = new JLabel("Password does not match!");
					er.setFont(new Font("Consolas",Font.PLAIN, 13));
					JOptionPane.showMessageDialog(null,er,"", JOptionPane.ERROR_MESSAGE);
					
				}

			}
	
		}
		
		// if the back button is pressed
		if(e.getSource() == back) {
			
			// disposing the frame
			this.dispose();
			
			// redirectin to login panel
			new LoginPanel(this.indi).getUserInfo();

		}
		
	}

}
