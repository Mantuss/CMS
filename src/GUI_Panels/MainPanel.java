package GUI_Panels;

// importing Database manage file from DBMS package
import DBMS.DatabaseManage;

// import libraries
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*  Main panel is the starting panel of this system. This panel allows you to select your role. Student, Instructor and Teacher has been separated from this panel.
 *  This panel simply contains 3 buttons (1) Student (2) Instructor and (3) Admin). After selecting your role this panel will redirect you to the login page.
 */
public class MainPanel implements ActionListener{
	
	// variable(class properties) declaration at the top so that it can be accessed from any part of the class
	// encapsulating by using the key word private
	private JButton instructor, student, admin , credit, exit;
	private JFrame frame;
	
	// creating an object of DatabaseManage Where all the queries are executed.
	private DatabaseManage manage = new DatabaseManage();
	
	// function to open the credintials in the webpage
	public void openWebPage(String url){
		   try {         
		     java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		   }
		   catch (java.io.IOException e) {
		       System.out.println(e.getMessage());
		   }
		}
	
	public void startGUI() {
		
		// frame for this panel has been created in the constructor
		frame = new JFrame("HCMS");
		frame.setBackground(Color.gray);
		
		// student Button in the panel
		student = new JButton();
		Icon studentIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\student.png");
		student.setIcon(studentIcon);
		student.setHorizontalAlignment(SwingConstants.LEFT);
		student.setBounds(40,20,225,50);
		student.addActionListener(this);
		student.setText("  Students");
		student.setFocusable(false);
		student.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(student);
		
		// instructor Button in the panel
		instructor= new JButton();
		instructor.setText("  Teachers");
		
		Icon instructorIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\instruct.png");
		instructor.setIcon(instructorIcon);
		instructor.setHorizontalAlignment(SwingConstants.LEFT);
		instructor.setBounds(40,80, 225,50);
		
		instructor.addActionListener(this);
		instructor.setFocusable(false);
		instructor.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(instructor);
		
		// admin Button in the panel
		admin = new JButton();
		admin.setBounds(40,140,225,50);
		Icon adminIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\admin.png");
		admin.setHorizontalAlignment(SwingConstants.LEFT);
		admin.setText("  Admin/Executive");
		admin.setIcon(adminIcon);
		admin.addActionListener(this);
		admin.setFocusable(false);
		admin.setFont(new Font("Consolas",Font.PLAIN,15));
		frame.add(admin);
		
		
		// exit Button in the panel
		Icon exitIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\exit.png");
		exit = new JButton();
		exit.setIcon(exitIcon);
		exit.setHorizontalAlignment(SwingConstants.LEFT);
		exit.setBounds(205,  200, 60, 40);
		exit.addActionListener(this);
		exit.setFocusable(false);
		exit.setFont(new Font("Consolas",Font.PLAIN,14));
		frame.add(exit);
		
		// credit Button in the panel
		Icon creditIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\credit.png");
		credit = new JButton("Credintials");
		credit.setIcon(creditIcon);
		credit.setHorizontalAlignment(SwingConstants.LEFT);
		credit.setBounds(40, 200, 160, 40);
		credit.addActionListener(this);
		credit.setFocusable(false);
		credit.setFont(new Font("Consolas",Font.PLAIN,14));
		frame.add(credit);
		
		// Setting the size of the frame
		frame.setSize(325,305);
		ImageIcon frameIcon = new ImageIcon("C:\\Users\\ma\\OneDrive\\Documents\\Object Oriented Design and Programming\\C_Management\\Course Management\\images\\main.png");
		frame.setIconImage(frameIcon.getImage());
		
		// Setting the layout of the frame as null to manually set the bounds of the component
		frame.setLayout(null);
		
		// Setting the visiblity of the  frame to true so that the user can view it
		frame.setVisible(true);
		
		// Setting the locationRelative to null so that the frame appears at the middle of the screen
		frame.setLocationRelativeTo(null); 
		
		// Making the frame not resizable(Fixed bounds)
		frame.setResizable(false);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// if the student button is pressed 
		if(e.getSource() == student) {
			
			// closing the MainPanel frame
			frame.dispose();

			// error Handling for SQLException
			try {
				
				// if there are any accounts in the session table, this query retrieves them and redirects the user to the previously logged in account.
				 String[] result = manage.getSession("SELECT * FROM session WHERE user_id != 0 AND indicator = 'Student'");
				 
				 // manage.getSession() returns true if there is an account in the session table.
				 if(result[0] == "true") {
					 
					 // constructing a StudentPanel object and passing the last logged in account's username as a parameter to the constructor
					 StudentPanel student = new StudentPanel(result[1]);
					 
					 // starting the GUI panel for studentProfile
					 student.startGUI();
					 
				 }

				 // if no session is found in the session table then it will redirect the user to the login page
				 else {
					 new LoginPanel("Student").getUserInfo();
				 }

			} 
			
			// if SQLException is found then StackTrace will be printed.
			catch (SQLException error) {
				
				error.printStackTrace();
				
			}
	
		}
		
		// if the instructor button is pressed
		if(e.getSource() == instructor) {
			
			// closing the main panel
			frame.dispose();
			
			try {
				
				// if there are any accounts in the session table, this query retrieves them and redirects the user to the previously logged in account.
				 String[] result = manage.getSession("SELECT * FROM session WHERE user_id != 0 AND indicator = 'Teacher'");
				 
				// manage.getSession() returns true if there is an account in the session table (Which means session exists)
				 
				 if(result[0] == "true") {
					 
					 // calling InstructorPanel Constructor and calling the method to start the GUI
					 new InstructorPanel(result[1]).startGUI();

				 }
				 
				 else {
					 
					// if no session is found in the session table then it will redirect the user to the login page
					 new LoginPanel("Teacher").getUserInfo();
					 
					 
				 }
					 

			} 
			
			// if SQLException is found then StackTrace will be printed.
			catch (SQLException error) {
				
				error.printStackTrace();
				
			}
	
		}
		
		
		// if the admin button is pressed
		if(e.getSource() == admin) {
			
			// closing the main panel
			frame.dispose();
			
			//redirecting the user to the login page
			LoginPanel log = new LoginPanel("Admin");
			log.getUserInfo();
		}
		
		// if credintial button is pressed
		if(e.getSource() == credit) {
			
			// opening the link in the browser
			openWebPage("https://github.com/Mantuss");
			
		}
		
		// if the exit button is pressed
		if(e.getSource() == exit) {
			
			// closing the frame
			frame.dispose();
			
		}
		
	}

	
	// Starting of the Course Management System
	public static void main(String[] args) {

		// Creating an object of MainPanel and starting the GUI
		MainPanel main = new MainPanel();
		main.startGUI();

	}

}
